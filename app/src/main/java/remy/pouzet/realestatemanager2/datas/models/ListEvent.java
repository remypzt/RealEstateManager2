package remy.pouzet.realestatemanager2.datas.models;
/**
 * Created by Remy Pouzet on 12/05/2021.
 */
public class ListEvent {
	public long id;
	
	public ListEvent(Long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
