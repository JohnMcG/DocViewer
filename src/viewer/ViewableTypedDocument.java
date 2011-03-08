package viewer;

public abstract class ViewableTypedDocument implements ViewableDocument {

	private String m_type;
	
	protected ViewableTypedDocument(String type) {
		m_type = type;
	}

	@Override
	public String getDocumentType() {
		return m_type;
	}

}