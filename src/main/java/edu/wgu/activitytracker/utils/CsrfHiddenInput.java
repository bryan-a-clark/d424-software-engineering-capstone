package edu.wgu.activitytracker.utils;

import gg.jte.Content;
import gg.jte.TemplateOutput;
import lombok.AllArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;

@AllArgsConstructor
public class CsrfHiddenInput implements Content {

    private final CsrfToken csrfToken;

    @Override
    public void writeTo(TemplateOutput templateOutput) {
        if (this.csrfToken != null) {
            templateOutput.writeContent("<input type=\"hidden\" name=\"%s\" value=\"%s\">"
                .formatted(csrfToken.getParameterName(), csrfToken.getToken()));
        }
    }
}
