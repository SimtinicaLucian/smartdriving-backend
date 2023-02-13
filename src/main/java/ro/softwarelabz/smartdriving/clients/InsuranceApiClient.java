package ro.softwarelabz.smartdriving.clients;

import ro.softwarelabz.smartdriving.clients.objects.InsuranceVerifyRequest;

public interface InsuranceApiClient {
    void checkInsurance(InsuranceVerifyRequest request);


}
