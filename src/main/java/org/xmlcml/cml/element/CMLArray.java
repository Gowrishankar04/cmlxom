package org.xmlcml.cml.element;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

import org.apache.log4j.Logger;
import org.xmlcml.cml.attribute.DelimiterAttribute;
import org.xmlcml.cml.attribute.NamespaceRefAttribute;
import org.xmlcml.cml.attribute.DelimiterAttribute.Action;
import org.xmlcml.cml.base.CMLAttribute;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLType;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.interfacex.HasArraySize;
import org.xmlcml.cml.interfacex.HasDataType;
import org.xmlcml.cml.interfacex.HasDictRef;
import org.xmlcml.cml.interfacex.HasUnits;
import org.xmlcml.euclid.EuclidRuntimeException;
import org.xmlcml.euclid.IntArray;
import org.xmlcml.euclid.RealArray;

/**
 * user-modifiable class supporting array. * autogenerated from schema use as a
 * shell which can be edited
 * 
 */
public class CMLArray extends AbstractArray implements HasUnits, HasArraySize,
		HasDataType, HasDictRef {

	static Logger logger = Logger.getLogger(CMLArray.class);
	/** namespaced element name. */
	public final static String NS = C_E + TAG;
	private DelimiterAttribute delimiterAttribute = null;

	/**
	 * constructor.
	 */
	public CMLArray() {
		init();
	}

	private void init() {
		ensureDelimiterAttribute(Action.RESET);
	}

	/**
	 * constructor.
	 * 
	 * @param old
	 */
	public CMLArray(CMLArray old) {
		super((AbstractArray) old);
		init();
	}

	/**
	 * copy node .
	 * 
	 * @return Node
	 */
	public Node copy() {
		return new CMLArray(this);
	}

	/**
	 * create new instance in context of parent, overridable by subclasses.
	 * 
	 * @param parent
	 *            parent of element to be constructed (ignored by default)
	 * @return CMLArray
	 */
	public CMLElement makeElementInContext(Element parent) {
		return new CMLArray();

	}

	/**
	 * check array is OK.
	 * 
	 * @param parent
	 *            element
	 */
	public void finishMakingElement(Element parent) {
		delimiterAttribute = null;
		delimiterAttribute = (DelimiterAttribute) this.getDelimiterAttribute();
		int ll = -1;
		int size = -1;
		if (this.getSizeAttribute() != null) {
			String aa = super.getAttributeValue("size");
			size = Integer.parseInt(aa);
		}
		String dataType = this.getDataType();
		if (dataType.equals(XSD_STRING)) {
			String[] ss = this.getStrings();
			ll = ss.length;
		} else if (XSD_DOUBLE.equals(CMLType.getNormalizedValue(dataType))) {
			try {
				double[] ss = this.getDoubles();
				ll = ss.length;
			} catch (RuntimeException e) {
				throw new RuntimeException("Fault in XML: "
						+ this.getXMLContent(), e);
			}
		} else if (dataType.equals(XSD_INTEGER)) {
			try {
				int[] ss = this.getInts();
				ll = ss.length;
			} catch (RuntimeException e) {
				throw new RuntimeException("cannot parse as ints: "
						+ this.getXMLContent());
			}
		} else if (dataType.equals(CML_DATATYPETYPE)) {
			String[] ss = this.getStrings();
			ll = ss.length;
		} else if (dataType.equals(CML_NAMESPACEREFTYPE)) {
			String[] ss = this.getStrings();
			ll = ss.length;
		} else if (dataType.equals(CML_UNITSTYPE)) {
			String[] ss = this.getStrings();
			ll = ss.length;
		} else {
			throw new RuntimeException("array does  not support dataType: "
					+ dataType);
		}
		if (size != -1 && ll != size) {
			throw new RuntimeException("Size attribute: " + size
					+ " incompatible with content: " + ll);
		}
		if (size != -1) {
			this.resetSize(ll);
		} else {
			this.removeAttribute("size");
		}
	}

	public void ensureDelimiterAttribute(Action action) {
		if (action.equals(Action.RESET)) {
			delimiterAttribute = null;
		}
		delimiterAttribute = (DelimiterAttribute) this.getDelimiterAttribute();
		if (delimiterAttribute == null) {
			delimiterAttribute = new DelimiterAttribute(S_SPACE);
			super.setDelimiter(S_SPACE);
		}
	}

	/**
	 * get delimiter
	 * 
	 * @return attribute
	 */
	public CMLAttribute getDelimiterAttribute() {
		delimiterAttribute = (DelimiterAttribute) super.getDelimiterAttribute();
		return delimiterAttribute;
	}

	// =========================== additional constructors
	// ========================

	/**
	 * formed from components. size is extracted from array dimensions sets
	 * dataType to xsd:string cannot use default delimiter (S_SPACE) if strings
	 * contain whitespace, so choose another delimiter
	 * 
	 * @param array
	 * @throws RuntimeException
	 *             strings must not contain whitespace
	 */
	public CMLArray(String[] array) throws RuntimeException {
		this.setArray(array);
	}

	/**
	 * formed from components. size is extracted from array dimensions sets
	 * dataType to xsd:string cannot use delimiter if strings contain it, so
	 * choose another delimiter
	 * 
	 * @param array
	 * @param delimiter
	 * @throws RuntimeException
	 *             strings must not contain delimiter
	 */
	public CMLArray(String[] array, String delimiter)
			throws RuntimeException {
		setDelimiter(delimiter);
		this.setArray(array);
	}

	/**
	 * formed from components. size is extracted from array dimensions sets
	 * dataType to xsd:double
	 * 
	 * @param array
	 */
	public CMLArray(RealArray array) {
		this.setArray(array.getArray());
	}

	/**
	 * formed from components. size is extracted from array dimensions sets
	 * dataType to xsd:double
	 * 
	 * @param array
	 */
	public CMLArray(double[] array) {
		this.setArray(array);
	}

	/**
	 * formed from components. size is extracted from array dimensions sets
	 * dataType to xsd:double cannot use delimiter if strings contain it, so
	 * choose another delimiter
	 * 
	 * @param array
	 * @param delimiter
	 * @throws RuntimeException
	 *             strings must not contain delimiter
	 */
	public CMLArray(double[] array, String delimiter)
			throws RuntimeException {
		setDelimiter(delimiter);
		this.setArray(array);
	}

	/**
	 * formed from components. size is extracted from array dimensions sets
	 * dataType to xsd:integer
	 * 
	 * @param array
	 */
	public CMLArray(int[] array) {
		this.setArray(array);
	}

	/**
	 * formed from components. size is extracted from array dimensions sets
	 * dataType to xsd:integer cannot use delimiter if strings contain it, so
	 * choose another delimiter
	 * 
	 * @param array
	 * @param delimiter
	 * @throws CMLException
	 *             doubles must not contain delimiter
	 */
	public CMLArray(int[] array, String delimiter) {
		setDelimiter(delimiter);
		this.setArray(array);
	}

	// ====================== housekeeping methods =====================

	/**
	 * get size of array.
	 * 
	 * @return size
	 */
	public int getArraySize() {
		return this.getSize();
	}

	/**
	 * returns array of primitive types based on dataType.
	 * 
	 * @return double[], int[], String[] or null
	 */
	public Object getPrimitiveArray() {
		Object primitiveArray = null;
		if (XSD_DOUBLE.equals(this.getDataType())) {
			primitiveArray = this.getDoubles();
		} else if (XSD_INTEGER.equals(this.getDataType())) {
			primitiveArray = this.getInts();
		} else if (XSD_STRING.equals(this.getDataType())) {
			primitiveArray = this.getStrings();
		} else {
			primitiveArray = this.getStrings();
		}
		return primitiveArray;
	}

	/**
	 * get strings.
	 * 
	 * @return strings
	 */
	public String[] getStrings() {
		String[] ss = null;
		if (this.getDataType().equals(XSD_STRING)) {
			ss = getSplitContent();
		}
		return ss;
	}

	/**
	 * splits content into tokens. if delimiter is whitespace, trims content and
	 * splits at whitespace (however long) else assume starts and ends with
	 * delimiter
	 * 
	 * @return the tokens
	 * @throws RuntimeException
	 *             if size attribute is inconsistent
	 */
	private String[] getSplitContent() throws RuntimeException {
		String[] ss = new String[0];
		String content = this.getXMLContent();
		if (content != null) {
			content = content.trim();
			ensureDelimiterAttribute(Action.PRESERVE);
			ss = delimiterAttribute.getSplitContent(content);
			int size = -1;
			if (this.getSizeAttribute() == null) {
				size = ss.length;
				setSize(size);
			} else {
				size = super.getSize();
				if (ss.length != size) {
					// FIXME this is not yet working
					// throw new CMLRuntime("Bad array length: "+size+"
					// incompatible
					// with elements: "+ss.length);
				}
			}
		}
		return ss;
	}

	/**
	 * get doubles.
	 * 
	 * @return doubles
	 * @throws RuntimeException
	 */
	public double[] getDoubles() throws RuntimeException {
		double[] dd = null;
		String dataType = this.getDataType();
		if (dataType != null
				&& XSD_DOUBLE.equals(CMLType.getNormalizedValue(dataType))) {
			String[] ss = getSplitContent();
			dd = new double[ss.length];
			for (int i = 0; i < dd.length; i++) {
				try {
					dd[i] = CMLUtil.parseFlexibleDouble(ss[i]);
				} catch (NumberFormatException nfe) {
					throw new RuntimeException("Bad double :" + ss[i]
							+ " at position: " + i, nfe);
				} catch (ParseException e) {
					throw new RuntimeException("Bad double : " + ss[i]
							+ "at position " + i, e);
				}
			}
		}
		return dd;
	}

	/**
	 * get ints.
	 * 
	 * @return ints
	 * @throws RuntimeException
	 */
	public int[] getInts() throws RuntimeException {
		int[] ii = null;
		String dataType = this.getDataType();
		if (XSD_INTEGER.equals(dataType)) {
			String[] ss = getSplitContent();
			ii = new int[ss.length];
			for (int i = 0; i < ii.length; i++) {
				try {
					ii[i] = new Integer(ss[i]).intValue();
				} catch (NumberFormatException nfe) {
					throw new RuntimeException("Bad int (" + ss[i]
							+ ") at position: " + i);
				}
			}
		}
		return ii;
	}

	/**
	 * returns the String value of the array. convenience method to avoid
	 * repeated accesses i.e. converts int and double to string
	 * 
	 * @return strings
	 */
	public List<String> getStringValues() {
		List<String> values = new ArrayList<String>();
		String dataType = this.getDataType();
		if (false) {
		} else if (dataType == null || dataType.equals(XSD_STRING)) {
			String[] strings = this.getStrings();
			for (String s : strings) {
				values.add(s);
			}
		} else if (XSD_INTEGER.equals(dataType)) {
			int[] ints = this.getInts();
			for (int i : ints) {
				values.add(S_EMPTY + i);
			}
		} else if (XSD_DOUBLE.equals(dataType)) {

			double[] doubles = this.getDoubles();
			for (double d : doubles) {
				values.add(S_EMPTY + d);
			}
		}
		return values;
	}

	// ====================== subsidiary accessors =====================

	/**
	 * sets components.
	 * 
	 * @param array
	 * @throws RuntimeException
	 */
	public void setArray(String[] array) throws RuntimeException {
		resetDataType(XSD_STRING);
		ensureDelimiterAttribute(Action.PRESERVE);
		for (String s : array) {
			delimiterAttribute.checkDelimiter(s);
		}
		setXMLContent(delimiterAttribute.getDelimitedXMLContent(array));
		resetSize(array.length);
	}

	private void resetDataType(String type) {
		Attribute a = (this.getAttribute("dataType"));
		if (a != null) {
			this.removeAttribute(a);
		}
		super.setDataType(type);
	}

	private void resetSize(int size) {
		Attribute a = (this.getAttribute("size"));
		if (a != null) {
			this.removeAttribute(a);
		}
		super.setSize(size);
	}

	/**
	 * sets components.
	 * 
	 * @param array
	 */
	public void setArray(double[] array) {
		resetDataType(XSD_DOUBLE);
		ensureDelimiterAttribute(Action.PRESERVE);
		setXMLContent(delimiterAttribute.getDelimitedXMLContent(array));
		resetSize(array.length);
	}

	/**
	 * sets components.
	 * 
	 * @param array
	 */
	public void setArray(int[] array) {
		resetDataType(XSD_INTEGER);
		ensureDelimiterAttribute(Action.PRESERVE);
		setXMLContent(delimiterAttribute.getDelimitedXMLContent(array));
		resetSize(array.length);
	}

	/**
	 * gets size of array.
	 * 
	 * @return int size of array
	 */
	public int getSize() {
		int size = -1;
		if (this.getSizeAttribute() != null) {
			size = super.getSize();
		} else {
			String[] array = this.getSplitContent();
			size = array.length;
		}
		return size;
	}

	// /**
	// * get delimiter. if none, use default CMLConstants.S_WHITEREGEX.
	// *
	// * @return delimiter
	// */

	// public String getDelimiter() {
	// String delim = super.getDelimiter();
	// if (delim == null || delim.equals(S_EMPTY)) {
	// delim = CMLConstants.S_WHITEREGEX;
	// }
	// return delim;
	// }

	/**
	 * reset null to whitespace, etc.
	 * 
	 * @return String
	 */
	public String getDelimiter() {
		String delimiter = super.getDelimiter();
		if (delimiter == null) {
			ensureDelimiterAttribute(Action.RESET);
			// delimiterAttribute = (DelimiterAttribute)
			// this.getDelimiterAttribute();
			// this.setDelimiter(S_SPACE);
			delimiter = delimiterAttribute.getValue();
		}
		return delimiter;
	}

	/**
	 * set delimiter.
	 * 
	 * @param value
	 */
	public void setDelimiter(String value) {
		ensureDelimiterAttribute(Action.RESET);
		super.setDelimiter(value);
		delimiterAttribute = (DelimiterAttribute) this.getDelimiterAttribute();

	}

	/**
	 * get dataType. if attribute not set, reset to String.
	 * 
	 * @return dataType (default XSD_STRING)
	 */
	public String getDataType() {
		String dataType = super.getDataType();
		if (dataType == null) {
			dataType = XSD_STRING;
			super.setDataType(dataType);
		}
		return CMLType.getNormalizedValue(dataType);
	}

	/**
	 * set dataType.
	 * 
	 * sets dataType. Cannot reset after array is populated
	 * 
	 * @param dType
	 *            (default XSD_STRING)
	 * @throws RuntimeException
	 *             attempt to reset datatype
	 */
	public void setDataType(String dType) {
		if (this.getDataTypeAttribute() != null) {
			throw new RuntimeException("Cannot reset dataType");
		}
		super.setDataType(dType);
	}

	/**
	 * set size.
	 * 
	 * @deprecated not user-accesible - throws CMLRuntime sets delimiter. Cannot
	 *             reset after array is populated if delimiter is whitespace,
	 *             removes the attribute
	 * @param s
	 *            the size
	 * @throws RuntimeException
	 *             attempt to reset datatype
	 */
	public void setSize(int s) {
		if (this.getSizeAttribute() != null) {
			throw new RuntimeException("user cannot reset size");
		}
		super.setSize(s);
	}

	// ====================== functionality =====================

	/**
	 * can two arrays be used for arithmetic. checks that both arrays are
	 * numeric and of same dataType and of same size
	 * 
	 * @param array
	 *            the array to test; can have different owner
	 * @throws CMLException
	 *             if not of same numeric data type and size
	 */
	public void checkNumericConformability(CMLArray array) {
		String thisDataType = this.getDataType();
		String arrayDataType = array.getDataType();
		if (thisDataType.equals(XSD_STRING)
				|| !thisDataType.equals(arrayDataType)
				|| this.getSize() != array.getSize()) {
			throw new RuntimeException(
					"Unsuitable dataTypes for numeric operations / "
							+ this.getDataType() + CMLConstants.S_SLASH + this.getSize()
							+ CMLConstants.S_SLASH + array.getDataType() + CMLConstants.S_SLASH
							+ array.getSize());
		}
	}

	/**
	 * subtract an array from this..
	 * 
	 * result = this - array, owner document = this does not alter this only
	 * works if both arrays are numeric and of same dataType
	 * 
	 * @param array
	 *            the array to subtract; can have different owner
	 * @throws CMLException
	 *             inappropriate dataTypes, unequal arrays
	 * @return new array
	 */
	public CMLArray subtract(CMLArray array) {
		checkNumericConformability(array);
		CMLArray resultArray = null;
		try {
			if (this.getDataType().equals(XSD_DOUBLE)) {
				// FIXME this appears to be wrong in euclid
				// RealArray result = new RealArray(this.getDoubles()).
				// subtract(new RealArray(array.getDoubles()));
				RealArray result = new RealArray(array.getDoubles())
						.subtract(new RealArray(this.getDoubles()));
				resultArray = new CMLArray(result.getArray());
			} else if (this.getDataType().equals(XSD_INTEGER)) {
				// FIXME this appears to be wrong in euclid
				// IntArray result = new IntArray(this.getInts()).
				// subtract(new IntArray(array.getInts()));
				IntArray result = new IntArray(array.getInts())
						.subtract(new IntArray(this.getInts()));
				resultArray = new CMLArray(result.getArray());
			}
		} catch (EuclidRuntimeException je) {
			throw new RuntimeException(S_EMPTY + je);
		}
		return resultArray;
	}

	/**
	 * add an array to this..
	 * 
	 * result is this + array, owner document = this does not alter this
	 * 
	 * only works if both arrays are numeric and of same dataType
	 * 
	 * @param array
	 *            the array to add; can have different owner
	 * @throws CMLException
	 *             inappropriate dataTypes, unequal arrays
	 * 
	 * @return the new array
	 */
	public CMLArray plus(CMLArray array) {
		checkNumericConformability(array);
		CMLArray resultArray = null;
		try {
			if (this.getDataType().equals(XSD_DOUBLE)) {
				RealArray result = new RealArray(this.getDoubles())
						.plus(new RealArray(array.getDoubles()));
				resultArray = new CMLArray(result.getArray());
			} else if (this.getDataType().equals(XSD_INTEGER)) {
				IntArray result = new IntArray(this.getInts())
						.plus(new IntArray(array.getInts()));
				resultArray = new CMLArray(result.getArray());
			}
		} catch (EuclidRuntimeException je) {
			throw new RuntimeException(S_EMPTY + je);
		}
		return resultArray;
	}

	/**
	 * add a string.
	 * 
	 * datatype must be unset or have been set to XSD_STRING
	 * 
	 * @param s
	 *            String to add
	 * 
	 * @throws RuntimeException
	 *             dataType not XSD_STRING
	 */
	public void append(String s) throws RuntimeException {
		String dataType = this.getDataType();
		if (!XSD_STRING.equals(dataType)) {
			throw new RuntimeException("Cannot add string (" + s
					+ ") to array of: " + dataType);
		}
		appendXML(s, 1);
	}

	/**
	 * add a double. datatype must have been set to XSD_DOUBLE
	 * 
	 * @param d
	 *            double to add
	 * @throws RuntimeException
	 *             dataType not XSD_DOUBLE
	 */
	public void append(double d) throws RuntimeException {
		String dataType = this.getDataType();
		if (!XSD_DOUBLE.equals(dataType)) {
			throw new RuntimeException("Cannot add double to array of: "
					+ dataType);
		}
		appendXML(S_EMPTY + d, 1);
	}

	/**
	 * add an integer. datatype must have been set to XSD_INTEGER
	 * 
	 * @param i
	 *            integer to add
	 * @throws RuntimeException
	 *             dataType not XSD_INTEGER
	 */
	public void append(int i) throws RuntimeException {
		String dataType = this.getDataType();
		if (!XSD_INTEGER.equals(dataType)) {
			throw new RuntimeException("Cannot add int to array of: "
					+ dataType);
		}
		appendXML(S_EMPTY + i, 1);
	}
	
	public void append(CMLArray array) {
		if (!this.getDataType().equals(array.getDataType())) {
			throw new RuntimeException("Cannot append array of different type: "+this.getDataType()+" != "+array.getDataType());
		}
		if (!this.getDelimiter().equals(array.getDelimiter())) {
			throw new RuntimeException("Cannot append array with different delimiter: "+this.getDelimiter()+" != "+array.getDelimiter());
		}
		String arrayString = array.getXMLContent();
		String delimiter = this.getDelimiter().trim();
		if (delimiter.length() > 0) {
			arrayString = arrayString.substring(1, arrayString.length()-1);
		}
		appendXML(arrayString, array.getSize());
	}

	private void appendXML(String s, int toAdd) {
		int size = (this.getSizeAttribute() == null) ? 0 : this.getSize();
		ensureDelimiterAttribute(Action.PRESERVE);
		if (toAdd <= 1) {
			delimiterAttribute.checkDelimiter(s);
		}
		String xmlContent = this.getXMLContent();
		String delimitedContent = delimiterAttribute.appendXMLContent(
				xmlContent, s);
		this.setXMLContent(delimitedContent);
		resetSize(size + toAdd);
	}

	/**
	 * sets units attribute. requires namespace for unit to be in scope.
	 * 
	 * @param prefix
	 *            for namespace
	 * @param id
	 *            for unit
	 * @param namespaceURI
	 *            sets units namespace if not present already
	 */
	public void setUnits(String prefix, String id, String namespaceURI) {
		NamespaceRefAttribute.setUnits((HasUnits) this, prefix, id,
				namespaceURI);
	}
}
