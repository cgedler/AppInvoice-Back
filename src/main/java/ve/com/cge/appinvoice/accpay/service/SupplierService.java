/* 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ve.com.cge.appinvoice.accpay.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import ve.com.cge.appinvoice.accpay.dto.SupplierDTO;
import ve.com.cge.appinvoice.accpay.model.Supplier;
import ve.com.cge.appinvoice.accpay.repository.ISupplierRepository;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * SupplierService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Service
public class SupplierService {

    private final ISupplierRepository supplierRepository;
    
    public SupplierService(ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    public List<Supplier> findSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList;
    }
       
    public SupplierDTO findSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null) {
            return new SupplierDTO(supplier.getId(), supplier.getDescription());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertSupplier(SupplierDTO request) {
        Supplier supplier = new Supplier(request.getId(),request.getDescription());
        supplierRepository.save(supplier);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateSupplier(SupplierDTO request, Long id) {
        Supplier supplier = new Supplier(id, request.getDescription());
        supplierRepository.save(supplier);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }
    
    public byte[] exporByIdToPdf(Long id) throws JRException, FileNotFoundException {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        String template = "templates/Base_details.jrxml";  
        return JasperExportManager.exportReportToPdf(getReport(supplier, template));
    }
    
    private JasperPrint getReport(Supplier supplier, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("id", supplier.getId().toString());
        params.put("description", supplier.getDescription());
        params.put("title","Supplier");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }
    
    public byte[] exportListToPdf() throws JRException, FileNotFoundException {
        List<Supplier> supplierList = supplierRepository.findAll();
        String template = "templates/Base.jrxml";  
        return JasperExportManager.exportReportToPdf(getListReport(supplierList, template));
    }

    public byte[] exportListToXls() throws JRException, FileNotFoundException {
        List<Supplier> supplierList = supplierRepository.findAll();
        String template = "templates/Base.jrxml";
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getListReport(supplierList, template)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getListReport(List<Supplier> list, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("suppliersData", new JRBeanCollectionDataSource(list));
        params.put("title","Suppliers List");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }
  
}
