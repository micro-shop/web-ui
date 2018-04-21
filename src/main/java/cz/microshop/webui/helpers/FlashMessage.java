package cz.microshop.webui.helpers;

import cz.microshop.webui.model.Order;
import cz.microshop.webui.model.OrderItem;
import cz.microshop.webui.model.User;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FlashMessage {

	public static void createFlashMessage(String alertType, String message, Model model) {
		model.addAttribute("flash", true);
		model.addAttribute("type", alertType);
		model.addAttribute("flashMessage", message);
	}
	
	public static void createFlashMessage(String alertType, String message, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("flash", true);
		redirectAttributes.addFlashAttribute("type", alertType);
		redirectAttributes.addFlashAttribute("flashMessage", message);
	}
	
	public static String createOrderContentsMessage(Order order, User user) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<html>");
		stringBuilder.append("<head>");
		stringBuilder.append("<style>");
		stringBuilder.append("table, th, td {");
		stringBuilder.append("font-family: arial, sans-serif;");
		stringBuilder.append("border-collapse: collapse;");
		stringBuilder.append("width: 100%;");
		stringBuilder.append("}");
		stringBuilder.append("td, th {");
		stringBuilder.append("border: 1px solid #dddddd;");
		stringBuilder.append("padding: 8px;");
		stringBuilder.append("}");
		stringBuilder.append("tr:nth-child(even) {");
		stringBuilder.append("background-color: #dddddd;");
		stringBuilder.append("}");
		stringBuilder.append("</style>");
		stringBuilder.append("</head>");
		stringBuilder.append("<body>");
		stringBuilder.append("<p>");
		stringBuilder.append("Dobrý den uživateli " + user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ").");
		stringBuilder.append("Děkujeme za nákup. Níže Vám zasíláme seznam zakoupeného zboží:");
		stringBuilder.append("</p>");
		stringBuilder.append("<table>");
		stringBuilder.append("<tr>");
		stringBuilder.append("<th>Produkt</th>");
		stringBuilder.append("<th>Množství</th>");
		stringBuilder.append("<th>Cena</th>");
		stringBuilder.append("</tr>");
		for(OrderItem orderDetails : order.getOrderItems()) {
			stringBuilder.append("<tr>");
			stringBuilder.append("<td>" + orderDetails.getProductName() + "</td>");
			stringBuilder.append("<td>" + orderDetails.getQuantity() + "</td>");
			stringBuilder.append("<td>" + orderDetails.getUnitPrice() + " Kč</td>");
			stringBuilder.append("</tr>");
		}
		stringBuilder.append("<tr>");
		stringBuilder.append("<td colspan=\"2\">Doprava: " + order.getShipping().getPrice().toString() + " Kč</td>");
		
		stringBuilder.append("<td>Celková cena: " + order.getTotalWithShipping().toString() + " Kč</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("</table>");
		stringBuilder.append("<p>");
		stringBuilder.append("Aktuální stav Vaší objednávky je: " + order.getStatus());
		stringBuilder.append("<br />O dalším postupu Vás budeme informovat prostřednicvím emailu.");
		stringBuilder.append("<br />");
		stringBuilder.append("<br />S pozdravem");
		stringBuilder.append("<br />Microshop");
		stringBuilder.append("</p>");
		stringBuilder.append("</body>");
		stringBuilder.append("</html>");
		return stringBuilder.toString();
	}
}
