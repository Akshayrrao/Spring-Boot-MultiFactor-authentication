package spring.multifactor.authentication.Services;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class MFAServices {

    // Generate QRecode
    public String generateMFAQRCode() {
        SecretGenerator generator = new DefaultSecretGenerator();
        String url = "https://www.google.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/Example:alice@gmail.com?secret="
                +generator.generate()+"&issuer=Example"; // google api to generate QRCode
        return url;
    }


    // Verify MFA otp
    public boolean validateMFAOTP(String code, String secret) {
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return verifier.isValidCode(secret, code); // Return otp entered is true or false
    }
}
