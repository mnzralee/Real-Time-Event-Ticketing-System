package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    private final TicketingLogService ticketingLogService;

    @Autowired
    public VendorService(VendorRepository vendorRepository, TicketingLogService ticketingLogService) {
        this.vendorRepository = vendorRepository;
        this.ticketingLogService = ticketingLogService;
    }

    /**
     * Creates and saves a new vendor.
     * @param vendor The vendor to create.
     * @return The created vendor.
     */
    public Vendor addVendor(Vendor vendor) {
        ticketingLogService.saveLog("Created A New Vendor");
        return vendorRepository.save(vendor);
    }

    /**
     * Retrieves a vendor by its ID.
     * @param id The ID of the vendor.
     * @return The vendor with the given ID.
     * @throws IllegalArgumentException if no vendor is found.
     */
    public Vendor getVendorById(Integer id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Vendor ID: " + id));
    }

    /**
     * Retrieves all vendors from the database.
     * @return A list of all vendors.
     */
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    /**
     * Updates an existing vendor.
     * @param id The ID of the vendor to update.
     * @param updatedVendor The new vendor details.
     * @return The updated vendor.
     * @throws IllegalArgumentException if no vendor is found.
     */
    public Vendor updateVendor(Integer id, Vendor updatedVendor) {
        Vendor existingVendor = getVendorById(id);
        existingVendor.setFirstName(updatedVendor.getFirstName());
        existingVendor.setLastName(updatedVendor.getLastName());
        existingVendor.setEmail(updatedVendor.getEmail());
        return vendorRepository.save(existingVendor);
    }

    /**
     * Deletes a vendor by its ID.
     * @param id The ID of the vendor to delete.
     * @throws IllegalArgumentException if no vendor is found.
     */
    public void deleteVendor(Integer id) {
        if (!vendorRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid Vendor ID: " + id);
        }
        vendorRepository.deleteById(id);
    }
}
