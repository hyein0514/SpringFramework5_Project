package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.*;

import mylab.notification.di.annot.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    NotificationManager notificationManager;

    @Test
    public void testNotificationManager() {

        assertNotNull(notificationManager);

        // 이메일 서비스 검증
        EmailNotificationService emailService =
                (EmailNotificationService) notificationManager.getEmailService();

        assertNotNull(emailService);

        assertEquals("smtp.gmail.com", emailService.getSmtpServer());

        assertEquals(587, emailService.getPort());

        // SMS 서비스 검증
        SmsNotificationService smsService =
                (SmsNotificationService) notificationManager.getSmsService();

        assertNotNull(smsService);

        assertEquals("SKT", smsService.getProvider());

        // 메서드 실행 테스트
        notificationManager.sendNotificationByEmail("테스트 이메일");

        notificationManager.sendNotificationBySms("테스트 SMS");

    }

}