package ro.softwarelabz.smartdriving.alert.email.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Mail {
    private String mailFrom;
    private String mailTo;
    private String mailSubject;
    private String mailContent;
    private String contentType;
    private List<Object> attachments;
}
