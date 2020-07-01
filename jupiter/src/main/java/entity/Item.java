package entity;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;


public class Item {
	// 若改为public，则别人可读可改，不安全， 故用private，后面用getter,setter
	// 如果getter和setter都有，和public没区别
	
	// primary key
	private String itemId;
	private String name;
	private String address;
	private Set<String> keywords;
	private String imageUrl;
	private String url;

	// 用itembuilder来build而不是自己写一个
	//（1）不想用户修改数据, 若想用户修改数据，直接添加set即可，不需要constructor，不想用户修改，故改为private，但para太多，故又需要builder
	//（2） 方便，traditional para too much (输入容易犯错，很难数到第几个，添加一个para所以代码都要改）；
	
	// here is a constructor
	private Item(ItemBuilder builder) {
		this.itemId = builder.itemId;
		this.name = builder.name;
		this.address = builder.address;
		this.imageUrl = builder.imageUrl;
		this.url = builder.url;
		this.keywords = builder.keywords;
	}

	// user can get the data
	public String getItemId() {
		return itemId;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public Set<String> getKeywords() {
		return keywords;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getUrl() {
		return url;
	}
    
	// item convert to json obj
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("item_id", itemId);
		obj.put("name", name);
		obj.put("address", address);
		obj.put("keywords", new JSONArray(keywords));
		obj.put("image_url", imageUrl);
		obj.put("url", url);
		return obj;
	}
    
	// 不和item class平行原因
	// constructor是private，如果平行builder也不可call constructor，故写在里面
	
	// static不需要constructor也可以有，i.e.汽车销售员和汽车
	// 不写成static: 需要汽车才可以把汽车销售员拽出来
	public static class ItemBuilder {
		private String itemId;
		private String name;
		private String address;
		private String imageUrl;
		private String url;
		private Set<String> keywords;
		
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public void setKeywords(Set<String> keywords) {
			this.keywords = keywords;
		}
	
		public Item build() {
			return new Item(this);
		}
	}
	
	

	
}
