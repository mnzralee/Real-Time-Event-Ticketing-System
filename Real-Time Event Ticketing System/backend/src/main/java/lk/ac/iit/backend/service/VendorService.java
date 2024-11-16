package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    /**
     * Logic to add vendor
     * @param vendor instance
     * @return vendor instance
     */
    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    /**
     * Logic to get a list of all vendors
     * @return  list of vendors
     */
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
