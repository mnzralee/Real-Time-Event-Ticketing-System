package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor getVendorById(Integer id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Vendor ID: " + id));
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor updateVendor(Integer id, Vendor updatedVendor) {
        Vendor existingVendor = getVendorById(id);
        existingVendor.setFirstName(updatedVendor.getFirstName());
        existingVendor.setLastName(updatedVendor.getLastName());
        existingVendor.setEmail(updatedVendor.getEmail());
        return vendorRepository.save(existingVendor);
    }

    public void deleteVendor(Integer id) {
        if (!vendorRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid Vendor ID: " + id);
        }
        vendorRepository.deleteById(id);
    }
}
