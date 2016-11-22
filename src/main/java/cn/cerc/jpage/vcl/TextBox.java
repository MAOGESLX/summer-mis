package cn.cerc.jpage.vcl;

import cn.cerc.jpage.core.Component;
import cn.cerc.jpage.core.HtmlWriter;

/**
 * 
 * 单行文本输入框
 * 
 * @author 张弓
 *
 */
public class TextBox extends Component {
	private Label caption;
	private String name;
	private String type;
	private Object value;
	private String maxlength;
	private String placeholder;
	private String dialog;
	private boolean readonly;
	private String onclick;

	public TextBox() {
		super();
	}

	public TextBox(Component owner) {
		super(owner);
	}

	@Override
	public void output(HtmlWriter html) {
		if (caption != null)
			caption.output(html);
		html.print("<input");
		if (this.getId() != null)
			html.print(" id='%s'", this.getId());
		if (this.name != null)
			html.print(" name='%s'", this.getName());
		if (type != null)
			html.print(" type=\"%s\"", type);
		if (maxlength != null)
			html.print(" maxlength=%s", this.maxlength);
		if (value != null)
			html.print(" value='%s'", this.value);
		if (onclick != null)
			html.print(" onclick='%s'", this.onclick);
		if (placeholder != null)
			html.print(" placeholder='%s'", this.placeholder);
		if (this.readonly)
			html.print(" readonly='readonly'");
		html.println(">");

		if (dialog != null) {
			html.print("<span>");
			html.print("<a href=\"javascript:%s('%s')\">", dialog, getId());
			html.print("<img src='images/select-pic.png'>");
			html.print("</a>");
			html.print("</span>");
		}
	}

	public Label getCaption() {
		if (caption == null)
			caption = new Label();
		return caption;
	}

	public void setCaption(Label caption) {
		this.caption = caption;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
}
