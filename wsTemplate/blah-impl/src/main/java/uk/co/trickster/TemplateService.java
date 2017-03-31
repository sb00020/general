package uk.co.trickster;

import uk.co.trickster.services.template.message.GetRequestMessage;
import uk.co.trickster.services.template.message.GetResponseMessage;

public interface TemplateService {

    public GetResponseMessage Test(GetRequestMessage message);

}
