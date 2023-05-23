package com.example.costume_rental.controller;

import com.example.costume_rental.dto.CostumeDTO;
import com.example.costume_rental.dto.PenaltyDTO;
import com.example.costume_rental.model.CostumeReturnDetail;
import com.example.costume_rental.model.Customer;
import com.example.costume_rental.model.Invoice;
import com.example.costume_rental.model.Penalty;
import com.example.costume_rental.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final CustomerService customerService;
    private final CostumeBorrowingDetailService costumeBorrowingDetailService;
    private final CostumeService costumeService;
    private final InvoiceService invoiceService;
    private final CostumeReturnDetailService costumeReturnDetailService;
    private final PenaltyService penaltyService;
    public static String TRANG_PHUC = "trangphucs";
    public static String KHACH_HANG = "khachhang";

    @GetMapping(value = "/findCustomer")
    public String displayGDTimKhacHang() {
        return "gdTimKhachHang";
    }
    @PostMapping(value = "/findCustomer")
    public String findListCustomers(@RequestParam String keyword, Model model) {
        var listCustomers = customerService.findListCustomers(keyword);
        if (CollectionUtils.isEmpty(listCustomers)) {
            model.addAttribute("keyword", keyword);
            model.addAttribute("notFoundLabel", "Không tìm thấy khách hàng!");
            return "gdTimKhachHang";
        }
        model.addAttribute("listKhachHang", listCustomers);
        return "gdTimKhachHang";
    }
    @GetMapping(value = "/listCostumes")
    public String displayGDDanhSachTrangPhuc(@RequestParam Integer customerId, HttpSession session, Model model) {
        List<CostumeDTO> listCostumesDTOS = costumeBorrowingDetailService.getListCostumes(customerId);
        Customer customer = customerService.getCustomer(customerId);
        session.setAttribute(KHACH_HANG, customer);
        if (CollectionUtils.isEmpty(listCostumesDTOS)) {
            model.addAttribute("notFoundLabel", "Không tìm thấy trang phục mượn!");
        } else {
            model.addAttribute("listCostumesDTOS", listCostumesDTOS);
        }
        session.setAttribute(TRANG_PHUC, listCostumesDTOS);
        return "gdDanhSachTrangPhuc";
    }
    @PostMapping(value = "/listCostumes")
    public String pay(@RequestParam("selectedCostumesInput") String selectedCostumesJson, Model model,
                                            HttpSession session){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> selectedCostumes;
        try {
            selectedCostumes = objectMapper.readValue(selectedCostumesJson, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Khong the chuyen sang JSON";
        }
        if (CollectionUtils.isEmpty(selectedCostumes)) {
            model.addAttribute("notFoundLabel", "Bạn chưa chọn trang phục nào để thanh toán!");
            return "gdDanhSachTrangPhuc";
        }
        List<CostumeDTO> listCostumesDTO = (List<CostumeDTO>) session.getAttribute(TRANG_PHUC);
        List<CostumeDTO> filteredCostumes = listCostumesDTO.stream()
                .filter(costume -> selectedCostumes.contains(costume.getCostumeId()))
                .collect(Collectors.toList());
        session.setAttribute(TRANG_PHUC, filteredCostumes);
        double totalMoney = 0;
        for (CostumeDTO costume : listCostumesDTO) {
            double rentCost = costume.getRentCost();
            int borrowedDays = costume.getBorrowedDays();
            double fine = costume.getFine()!= null ? costume.getFine() : 0;
            totalMoney += rentCost * borrowedDays + fine;
        }
        model.addAttribute("totalMoney", totalMoney);
        double totalDeposit = filteredCostumes.get(0).getTotalDeposit();
        model.addAttribute("totalDeposit", totalDeposit);
        double refundMoney = Math.abs(totalMoney - filteredCostumes.get(0).getTotalDeposit());
        model.addAttribute("refundMoney", refundMoney);
        return "gdHoaDon";
    }
    @GetMapping(value = "/penalty")
    public String displayGDTienPhat(@RequestParam Integer costumeId, Model model) {
        model.addAttribute("costumeId", costumeId);
        model.addAttribute("penaltyDTO", new PenaltyDTO());
        return "gdTienPhat";
    }
    @PostMapping(value = "/penalty")
    public String enterFines(@Valid @ModelAttribute("penaltyDTO") PenaltyDTO penaltyDTO,
                                    BindingResult bindingResult, Model model,
                                    @RequestParam Integer costumeId, HttpSession session) {
        if (bindingResult.hasErrors()) {
            //co loi trong form nhap tien phat
            model.addAttribute("costumeId", costumeId);
            return "gdTienPhat";
        }
        List<CostumeDTO> listCostumesDTO = (List<CostumeDTO>) session.getAttribute(TRANG_PHUC);
        Optional<CostumeDTO> foundCostumeDTO = listCostumesDTO.stream()
                .filter(costomeDTO -> Objects.equals(costomeDTO.getCostumeId(), costumeId))
                .findFirst();

        if (foundCostumeDTO.isPresent()) {
            CostumeDTO costomeDTO = foundCostumeDTO.get();
            costomeDTO.setStatus(penaltyDTO.getStatus());
            costomeDTO.setFine(penaltyDTO.getFine());
        } else {
            System.out.println("CostumeId không tồn tại");
        }
        model.addAttribute("listCostumesDTOS", listCostumesDTO);
        session.setAttribute(TRANG_PHUC, listCostumesDTO);
        return "gdDanhSachTrangPhuc";
    }
    @PostMapping(value = "/invoice")
    public String confirm(HttpSession session, Model model, @RequestParam Double totalMoney, @RequestParam Double refundMoney) {
        List<CostumeDTO> costumeDTOList = (List<CostumeDTO>) session.getAttribute(TRANG_PHUC);
        if (CollectionUtils.isEmpty(costumeDTOList)) {
            model.addAttribute("notFoundLabel", "Bạn chưa chọn trang phục trả!");
            return "gdHoaDon";
        }
        Customer customer = (Customer) session.getAttribute(KHACH_HANG);
        List<CostumeReturnDetail> costumeReturnDetails = new ArrayList<>();
        for (CostumeDTO costumeDTO: costumeDTOList){
            CostumeReturnDetail costumeReturnDetail = new CostumeReturnDetail();
            costumeReturnDetail.setQuantityReturn(costumeDTO.getQuantity());
            costumeReturnDetail.setBorrowedDays(costumeDTO.getBorrowedDays());
            costumeReturnDetail.setCostumeBorrowingDetail(costumeBorrowingDetailService.findById(costumeDTO.getCostumeBorrowingDetailId()));
            costumeReturnDetails.add(costumeReturnDetail);

            costumeReturnDetailService.save(costumeReturnDetail);
            if (costumeDTO.getFine()!= null){
                Penalty penalty = new Penalty();
                penalty.setFine(costumeDTO.getFine());
                penalty.setStatus(costumeDTO.getStatus());
                penalty.setCostumeReturnDetail(costumeReturnDetail);
                penaltyService.save(penalty);
            }
        }
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setTime(new Date());
        invoice.setTotalMoney(totalMoney);
        invoice.setRefunds(refundMoney);
        invoice.setCostumeReturnDetails(costumeReturnDetails);

        invoiceService.save(invoice);

        model.addAttribute("successLabel", "Thanh toán thành công!");
        return "gdTimKhachHang";
    }

}
