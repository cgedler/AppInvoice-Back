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

package ve.com.cge.appinvoice.items.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import ve.com.cge.appinvoice.items.dto.ItemDTO;
import ve.com.cge.appinvoice.items.repository.IItemRepository;
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
import ve.com.cge.appinvoice.items.model.Item;

/**
 * ItemService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 14, 2024
 */
@Service
public class ItemService {

    private final IItemRepository itemRepository;

    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public List<Item> findItems() {
        List<Item> itemsList = itemRepository.findAll();
        return itemsList;
    }

    public ItemDTO findItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            return new ItemDTO(item.getId(), item.getDescription(), item.getCategory(), item.getStock(), item.getPrice());
        }
        return null;
    }
    
    public List<Item> findItemByCategoryId(Long id) {
        List<Item> itemsList = itemRepository.findByCategoryId(id);
        return itemsList;   
    }
    
    @Transactional
    public UserResponse insertItem(ItemDTO request) {
        Item item = new Item(request.getId(), request.getDescription(), request.getCategory(), request.getStock(), request.getPrice());
        itemRepository.save(item);
        return new UserResponse("The new data was create");
    }
    
    @Transactional
    public UserResponse updateItem(ItemDTO request, Long id) {
        Item item = new Item(request.getId(), request.getDescription(), request.getCategory(), request.getStock(), request.getPrice());
        itemRepository.save(item);
        return new UserResponse("The data was update");
    }
     
    @Transactional
    public UserResponse deleteItem(Long id) {
        itemRepository.deleteById(id);
        return new UserResponse("The data was delete");
    }
    
    public byte[] exporByIdToPdf(Long id) throws JRException, FileNotFoundException {
        Item item = itemRepository.findById(id).orElse(null);
        String template = "templates/Base_Item_Det.jrxml";  
        return JasperExportManager.exportReportToPdf(getReport(item, template));
    }
    
    private JasperPrint getReport(Item item, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("id", item.getId().toString());
        params.put("description", item.getDescription());
        params.put("category", item.getCategory().getDescription());
        params.put("price", item.getPrice().getPrice().toString());
        params.put("stock", item.getStock().getQuantity().toString());
        params.put("title","Item");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }
    
    public byte[] exportListToPdf() throws JRException, FileNotFoundException {
        List<Item> itemList = itemRepository.findAll();
        String template = "templates/Base_Item.jrxml";  
        return JasperExportManager.exportReportToPdf(getListReport(itemList, template));
    }

    public byte[] exportListToXls() throws JRException, FileNotFoundException {
        List<Item> itemList = itemRepository.findAll();
        String template = "templates/Base_Item.jrxml";
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getListReport(itemList, template)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getListReport(List<Item> list, String nameReport) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:templates/invoice_logo.png").getAbsolutePath());
        params.put("itemsData", new JRBeanCollectionDataSource(list));
        params.put("title","Items List");
        params.put("logo", logoStream);
        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:" + nameReport)
                        .getAbsolutePath()), params, new JREmptyDataSource());
        return report;
    }

}