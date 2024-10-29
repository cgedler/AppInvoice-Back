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

package ve.com.cge.appinvoice.finance.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import ve.com.cge.appinvoice.finance.dto.TaxesDTO;
import ve.com.cge.appinvoice.finance.model.Taxes;
import ve.com.cge.appinvoice.finance.repository.ITaxesRepository;
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
 * TaxesService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class TaxesService {

    private final ITaxesRepository taxesRepository;

    public TaxesService(ITaxesRepository taxesRepository) {
        this.taxesRepository = taxesRepository;
    }
    
    public List<Taxes> findTaxess() {
        List<Taxes> taxesList = taxesRepository.findAll();
        return taxesList;
    }
       
    public TaxesDTO findTaxesById(Long id) {
        Taxes taxes = taxesRepository.findById(id).orElse(null);
        if (taxes != null) {
            return new TaxesDTO(taxes.getId(), taxes.getDescription(), taxes.getTax());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertTaxes(TaxesDTO request) {
        Taxes taxes = new Taxes(request.getId(),request.getDescription(), request.getTax());
        taxesRepository.save(taxes);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateTaxes(TaxesDTO request, Long id) {
        Taxes taxes = new Taxes(id, request.getDescription(), request.getTax());
        taxesRepository.save(taxes);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteTaxes(Long id) {
        taxesRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }
    
    public byte[] exporByIdToPdf(Long id) throws JRException, FileNotFoundException {
        Taxes taxes = taxesRepository.findById(id).orElse(null);
        String template = "templates/Base_Taxes_Det.jrxml";  
        return JasperExportManager.exportReportToPdf(getReport(taxes, template));
    }
    
    private JasperPrint getReport(Taxes taxes, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("id", taxes.getId().toString());
        params.put("description", taxes.getDescription());
        params.put("description", taxes.getTax());
        params.put("title","Taxes");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }
    
    public byte[] exportListToPdf() throws JRException, FileNotFoundException {
        List<Taxes> taxesList = taxesRepository.findAll();
        String template = "templates/Base_Taxes.jrxml";  
        return JasperExportManager.exportReportToPdf(getListReport(taxesList, template));
    }

    public byte[] exportListToXls() throws JRException, FileNotFoundException {
        List<Taxes> taxesList = taxesRepository.findAll();
        String template = "templates/Base_Taxes.jrxml";
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getListReport(taxesList, template)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getListReport(List<Taxes> list, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("taxesData", new JRBeanCollectionDataSource(list));
        params.put("title","Taxess List");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }

}
