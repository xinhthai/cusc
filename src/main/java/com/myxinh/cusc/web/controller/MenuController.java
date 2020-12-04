package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.service.MenuService;
import com.myxinh.cusc.service.dto.ui.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired

    MenuService menuService;

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDTO>> getMenu(){
        return ResponseEntity.ok(menuService.getAll());
    }
}
