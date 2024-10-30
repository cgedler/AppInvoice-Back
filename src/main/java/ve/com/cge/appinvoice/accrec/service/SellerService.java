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

package ve.com.cge.appinvoice.accrec.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
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
import ve.com.cge.appinvoice.accrec.repository.ISellerRepository;
import ve.com.cge.appinvoice.accrec.dto.SellerDTO;
import ve.com.cge.appinvoice.accrec.model.Seller;
import ve.com.cge.appinvoice.config.user.UserResponse;

/**
 * SellerService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class SellerService {
    
    private final ISellerRepository sellerRepository;

    public SellerService(ISellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    
    public List<Seller> findSellers() {
        List<Seller> sellerList = sellerRepository.findAll();
        return sellerList;
    }
       
    public SellerDTO findSellerById(Long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller != null) {
            return new SellerDTO(seller.getId(), seller.getDescription());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertSeller(SellerDTO request) {
        Seller seller = new Seller(request.getId(),request.getDescription());
        sellerRepository.save(seller);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateSeller(SellerDTO request, Long id) {
        Seller seller = new Seller(id, request.getDescription());
        sellerRepository.save(seller);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteSeller(Long id) {
        sellerRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }
    
    public byte[] exporByIdToPdf(Long id) throws JRException, FileNotFoundException {
        Seller seller = sellerRepository.findById(id).orElse(null);
        String template = "templates/Base_details.jrxml";  
        return JasperExportManager.exportReportToPdf(getReport(seller, template));
    }
    
    private JasperPrint getReport(Seller seller, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("id", seller.getId().toString());
        params.put("description", seller.getDescription());
        params.put("title","Seller");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }
    
    public byte[] exportListToPdf() throws JRException, FileNotFoundException {
        List<Seller> sellerList = sellerRepository.findAll();
        String template = "templates/Base.jrxml";  
        return JasperExportManager.exportReportToPdf(getListReport(sellerList, template));
    }

    public byte[] exportListToXls() throws JRException, FileNotFoundException {
        List<Seller> sellerList = sellerRepository.findAll();
        String template = "templates/Base.jrxml";
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getListReport(sellerList, template)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getListReport(List<Seller> list, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("listData", new JRBeanCollectionDataSource(list));
        params.put("title","Sellers List");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }

}
