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
package ve.com.cge.appinvoice.accpay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

/**
 * ShoppingByYearDTO
 *
 * @author Christopher Gedler <cgedler@gmail.com>
 * @version 1.0
 * @since Oct 29, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingByYearDTO {

    private BigDecimal january;
    private BigDecimal february;
    private BigDecimal march;
    private BigDecimal april;
    private BigDecimal may;
    private BigDecimal june;
    private BigDecimal july;
    private BigDecimal august;
    private BigDecimal september;
    private BigDecimal october;
    private BigDecimal november;
    private BigDecimal december;

    public ShoppingByYearDTO() {
        this.january = BigDecimal.ZERO;
        this.february = BigDecimal.ZERO;
        this.march = BigDecimal.ZERO;
        this.april = BigDecimal.ZERO;
        this.may = BigDecimal.ZERO;
        this.june = BigDecimal.ZERO;
        this.july = BigDecimal.ZERO;
        this.august = BigDecimal.ZERO;
        this.september = BigDecimal.ZERO;
        this.october = BigDecimal.ZERO;
        this.november = BigDecimal.ZERO;
        this.december = BigDecimal.ZERO;
    }

    public BigDecimal getJanuary() {
        return january;
    }

    public void setJanuary(BigDecimal january) {
        this.january = this.january.add(january);
    }

    public BigDecimal getFebruary() {
        return february;
    }

    public void setFebruary(BigDecimal february) {
        this.february = this.february.add(february);
    }

    public BigDecimal getMarch() {
        return march;
    }

    public void setMarch(BigDecimal march) {
        this.march = this.march.add(march);
    }

    public BigDecimal getApril() {
        return april;
    }

    public void setApril(BigDecimal april) {
        this.april = this.april.add(april);
    }

    public BigDecimal getMay() {
        return may;
    }

    public void setMay(BigDecimal may) {
        this.may = this.may.add(may);
    }

    public BigDecimal getJune() {
        return june;
    }

    public void setJune(BigDecimal june) {
        this.june = this.june.add(june);
    }

    public BigDecimal getJuly() {
        return july;
    }

    public void setJuly(BigDecimal july) {
        this.july = this.july.add(july);
    }

    public BigDecimal getAugust() {
        return august;
    }

    public void setAugust(BigDecimal august) {
        this.august = this.august.add(august);
    }

    public BigDecimal getSeptember() {
        return september;
    }

    public void setSeptember(BigDecimal september) {
        this.september = this.september.add(september);
    }

    public BigDecimal getOctober() {
        return october;
    }

    public void setOctober(BigDecimal october) {
        this.october = this.october.add(october);
    }

    public BigDecimal getNovember() {
        return november;
    }

    public void setNovember(BigDecimal november) {
        this.november = this.november.add(november);
    }

    public BigDecimal getDecember() {
        return december;
    }

    public void setDecember(BigDecimal december) {
        this.december = this.december.add(december);
    }

    @Override
    public String toString() {
        return "ShoppingByYearDTO{" + "january=" + january + ", february=" + february + ", march=" + march + ", april=" + april + ", may=" + may + ", june=" + june + ", july=" + july + ", august=" + august + ", september=" + september + ", october=" + october + ", november=" + november + ", december=" + december + '}';
    }
 
}
