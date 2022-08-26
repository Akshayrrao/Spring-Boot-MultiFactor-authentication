package spring.multifactor.authentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.multifactor.authentication.Services.MFAServices;

@RestController
public class MFAController {

    @Autowired
    public MFAServices mfaServices;

    @GetMapping("/generate")
    public String generateToken(){
        return mfaServices.generateMFAQRCode();
    }

    @PostMapping("/verify")
    public Boolean verify(@RequestParam String code, @RequestParam String secret){
        return mfaServices.validateMFAOTP(code,secret);
    }
}
