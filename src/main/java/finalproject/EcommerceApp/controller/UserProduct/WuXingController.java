package finalproject.EcommerceApp.controller.UserProduct;

import finalproject.EcommerceApp.dto_request.WuXingRequestDTO;
import finalproject.EcommerceApp.dto_response.WuXingResponseDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.WuXingFactory;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.WuXing;
import finalproject.EcommerceApp.service.SystemUserService;
import finalproject.EcommerceApp.service.WuXingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(name = "/api/users/wuxing")
public class WuXingController {
    @Autowired
    private WuXingService wuXingService;

    @Autowired
    private WuXingFactory wuXingFactory;

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping
    public ResponseEntity<WuXingResponseDTO> createWuXing(@Valid @RequestBody WuXingRequestDTO requestDTO,
                                                          Principal principal) {
        WuXingResponseDTO wuXingResponseDTO = wuXingFactory.toResponseDTO(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(wuXingResponseDTO);
    }

    @GetMapping
    public ResponseEntity<WuXingResponseDTO> getWuXing(Principal principal) throws ResourceNotFoundException {
        SystemUser systemUser = systemUserService.findByExternalUserId(principal.getName());
        WuXing wuXing = wuXingService.findBySystemUser(systemUser);
        WuXingResponseDTO wuXingResponseDTO = wuXingFactory.toResponseDTO(wuXing);
        return ResponseEntity.status(HttpStatus.CREATED).body(wuXingResponseDTO);
    }

    @PutMapping
    public ResponseEntity<WuXingResponseDTO> updateWuXing(@Valid @RequestBody WuXingRequestDTO requestDTO,
                                                          Principal principal) throws ResourceNotFoundException {
        SystemUser systemUser = systemUserService.findByExternalUserId(principal.getName());
        WuXing wuXing = wuXingService.updateWuXing(requestDTO, systemUser);
        WuXingResponseDTO wuXingResponseDTO = wuXingFactory.toResponseDTO(wuXing);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(wuXingResponseDTO);
    }

   // No need delete end point


}
