package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return vendorService.addVendor(vendor);
    }

    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Integer id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Integer id, @RequestBody Vendor vendor) {
        return vendorService.updateVendor(id, vendor);
    }

    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable Integer id) {
        vendorService.deleteVendor(id);
    }
}

