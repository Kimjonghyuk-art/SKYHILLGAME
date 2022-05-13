package yedam.game.skyhill.VO;

import javax.ws.rs.Consumes;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class InventoryVO {
	WeaponVO weaponvo;
	int ccode;
	int itemcode;
	String name;
	int effect;
	String grade;
	int count;
	
	public InventoryVO(){};
	
	
}
