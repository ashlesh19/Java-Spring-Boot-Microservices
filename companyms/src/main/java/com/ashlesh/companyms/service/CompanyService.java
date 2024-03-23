package com.ashlesh.companyms.service;



import com.ashlesh.companyms.dto.ReviewMessage;
import com.ashlesh.companyms.model.Company;

import java.util.List;


public interface CompanyService {

    List<Company> getAllCompanies();

    boolean updateCompany(Company company, Long id);

    void createCompany(Company company);

    boolean deleteCompanyById(Long id);

    Company getCompanyById(Long id);

    public void updateCompanyRating(ReviewMessage reviewMessage);

}
