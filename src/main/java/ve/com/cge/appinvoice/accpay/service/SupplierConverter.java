/*
 * Copyright (C) 2024 cge
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ve.com.cge.appinvoice.accpay.service;

import org.springframework.stereotype.Service;
import ve.com.cge.appinvoice.accpay.dto.SupplierDTO;
import ve.com.cge.appinvoice.accpay.model.Supplier;
import ve.com.cge.appinvoice.generic.AbstractDtoConverter;

/**
 * SupplierService 
 * 
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Mar 22, 2024
 */
@Service
public class SupplierConverter extends AbstractDtoConverter<Supplier, SupplierDTO> {

    @Override
    public Supplier fromDto(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setDescription(dto.getDescription());
        return supplier;
    }

    @Override
    public SupplierDTO fromEntity(Supplier entity) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    
}
