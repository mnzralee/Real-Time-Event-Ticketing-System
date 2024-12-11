package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Adds a new vendor to the system.
     * This method handles the HTTP POST request to create a new vendor.
     *
     * @param vendor The vendor object to be added.
     * @return The newly created vendor object with a generated ID.
     */
    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return vendorService.addVendor(vendor);
    }

    /**
     * Retrieves a vendor by their ID.
     * This method handles the HTTP GET request to fetch a vendor's details using their ID.
     *
     * @param id The ID of the vendor to retrieve.
     * @return The vendor object with the specified ID.
     */
    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Integer id) {
        return vendorService.getVendorById(id);
    }

    /**
     * Retrieves a list of all vendors in the system.
     * This method handles the HTTP GET request to fetch all vendors.
     *
     * @return A list of all vendor objects in the system.
     */
    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    /**
     * Updates an existing vendor's details.
     * This method handles the HTTP PUT request to update the vendor's information.
     *
     * @param id The ID of the vendor to update.
     * @param vendor The updated vendor object.
     * @return The updated vendor object.
     */
    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Integer id, @RequestBody Vendor vendor) {
        return vendorService.updateVendor(id, vendor);
    }

    /**
     * Deletes a vendor from the system.
     * This method handles the HTTP DELETE request to remove a vendor by their ID.
     *
     * @param id The ID of the vendor to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable Integer id) {
        vendorService.deleteVendor(id);
    }
}