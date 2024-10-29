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
import ve.com.cge.appinvoice.finance.dto.BankDTO;
import ve.com.cge.appinvoice.finance.model.Bank;
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
import ve.com.cge.appinvoice.finance.repository.IBankRepository;

/**
 * BankService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 23, 2024
 */
@Service
public class BankService {

    private final IBankRepository bankRepository;

    public BankService(IBankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    
    public List<Bank> findBanks() {
        List<Bank> bankList = bankRepository.findAll();
        return bankList;
    }
       
    public BankDTO findBankById(Long id) {
        Bank bank = bankRepository.findById(id).orElse(null);
        if (bank != null) {
            return new BankDTO(bank.getId(), bank.getDescription(), bank.getNumber());
        }
        return null;
    }
    
    @Transactional
    public UserResponse insertBank(BankDTO request) {
        Bank bank = new Bank(request.getId(),request.getDescription(), request.getNumber());
        bankRepository.save(bank);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateBank(BankDTO request, Long id) {
        Bank bank = new Bank(id, request.getDescription(), request.getNumber());
        bankRepository.save(bank);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteBank(Long id) {
        bankRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }
    
    public byte[] exporByIdToPdf(Long id) throws JRException, FileNotFoundException {
        Bank bank = bankRepository.findById(id).orElse(null);
        String template = "templates/Base_Bank_Det.jrxml";  
        return JasperExportManager.exportReportToPdf(getReport(bank, template));
    }
    
    private JasperPrint getReport(Bank bank, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("id", bank.getId().toString());
        params.put("description", bank.getDescription());
        params.put("number", bank.getNumber());
        params.put("title","Bank");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }
    
    public byte[] exportListToPdf() throws JRException, FileNotFoundException {
        List<Bank> bankList = bankRepository.findAll();
        String template = "templates/Base_Bank.jrxml";  
        return JasperExportManager.exportReportToPdf(getListReport(bankList, template));
    }

    public byte[] exportListToXls() throws JRException, FileNotFoundException {
        List<Bank> bankList = bankRepository.findAll();
        String template = "templates/Base_Bank.jrxml";
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getListReport(bankList, template)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getListReport(List<Bank> list, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("banksData", new JRBeanCollectionDataSource(list));
        params.put("title","Banks List");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }
    
}
