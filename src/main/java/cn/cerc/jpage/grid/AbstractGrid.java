package cn.cerc.jpage.grid;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jpage.common.DataView;
import cn.cerc.jpage.core.ActionForm;
import cn.cerc.jpage.core.Component;
import cn.cerc.jpage.core.HtmlWriter;
import cn.cerc.jpage.fields.AbstractField;
import cn.cerc.jpage.fields.IField;
import cn.cerc.jui.vcl.columns.AbstractColumn;

public abstract class AbstractGrid extends Component implements DataView {
	// 数据源
	private DataSet dataSet;
	// PC专用表格列
	private List<IField> columns = new ArrayList<>();
	// 当前样式选择
	private String CSSClass_PC = "dbgrid";
	private String CSSClass_Phone = "context";
	private String CSSStyle;
	// 分页控制
	private MutiPage pages = new MutiPage();
	// 是否允许修改
	private boolean readonly = true;
	//
	private HttpServletRequest request;
	protected ActionForm form;
	private String primaryKey;
	private List<RowLine> lines = new ArrayList<>();

	public AbstractGrid(Component owner) {
		super(owner);
		this.setId("grid");
		RowLine line = new RowLine(this);
		lines.add(line);
	}

	public AbstractGrid() {
		this(null);
	}

	@Override
	public Record getRecord() {
		return dataSet.getCurrent();
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		if (this.dataSet == dataSet)
			return;
		this.dataSet = dataSet;
		if (request == null)
			throw new RuntimeException("request is null");

		int pageno = 1;
		String tmp = request.getParameter("pageno");
		if (tmp != null && !tmp.equals("")) {
			pageno = Integer.parseInt(tmp);
		}
		pages.setRecordCount(dataSet.size());
		pages.setCurrent(pageno);
	}

	public void addField(IField field) {
		columns.add(field);
	}

	public String getCSSClass_PC() {
		return CSSClass_PC;
	}

	public void setCSSClass_PC(String cSSClass_PC) {
		CSSClass_PC = cSSClass_PC;
	}

	public String getCSSClass_Phone() {
		return CSSClass_Phone;
	}

	public void setCSSClass_Phone(String cSSClass_Phone) {
		CSSClass_Phone = cSSClass_Phone;
	}

	public String getCSSStyle() {
		return CSSStyle;
	}

	public void setCSSStyle(String cSSStyle) {
		CSSStyle = cSSStyle;
	}

	public MutiPage getPages() {
		return pages;
	}

	public List<IField> getColumns() {
		return this.columns;
	}

	public List<AbstractField> getFields() {
		List<AbstractField> items = new ArrayList<>();
		for (IField obj : columns) {
			if (obj instanceof AbstractField)
				items.add((AbstractField) obj);
		}
		return items;
	}

	@Override
	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		if (this.readonly == readonly)
			return;
		for (IField column : this.getColumns()) {
			if (column instanceof AbstractField)
				((AbstractField) column).setReadonly(readonly);
			else if (column instanceof AbstractColumn)
				((AbstractColumn) column).setReadonly(readonly);
		}
		this.readonly = readonly;
	}

	@Override
	public int getRecNo() {
		return dataSet.getRecNo();
	}

	@Deprecated
	public ActionForm getForm() {
		return form;
	}

	// FIXME: 此函数后需要去除！
	public void setForm(ActionForm form) {
		this.form = form;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public RowLine getLine(int index){
		return lines.get(index);
	}
	
	public abstract void outputGrid(HtmlWriter html);

	public abstract Component getExpender();
}
