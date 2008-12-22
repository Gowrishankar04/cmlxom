package org.xmlcml.cml.element;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;

import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLElements;

/**
 * user-modifiable class supporting metadataList. * autogenerated from schema
 * use as a shell which can be edited
 *
 */
public class CMLMetadataList extends AbstractMetadataList {

	/** namespaced element name.*/
	public final static String NS = C_E+TAG;

    /**
     * constructor.
     */
    public CMLMetadataList() {
    }

    /**
     * constructor.
     *
     * @param old
     */
    public CMLMetadataList(CMLMetadataList old) {
        super((AbstractMetadataList) old);

    }

    /**
     * copy node .
     *
     * @return Node
     */
    public Node copy() {
        return new CMLMetadataList(this);

    }

    /**
     * create new instance in context of parent, overridable by subclasses.
     *
     * @param parent
     *            parent of element to be constructed (ignored by default)
     * @return CMLMetadataList
     */
    public CMLElement makeElementInContext(Element parent) {
        return new CMLMetadataList();

    }

    /**
     * gets all nested metadata descendants of arbitrary CMLElement.
     *
     * recurses through all metadataList and metadata children, adding the
     * latter to the list. Order is not preserved.
     *
     * @return list of all descendants
     */
    public List<CMLMetadata> getMetadataDescendants() {
        List<CMLMetadata> mList = new ArrayList<CMLMetadata>();
        CMLElements<CMLMetadataList> metadataLists = this
                .getMetadataListElements();
        for (CMLMetadataList ml : metadataLists) {
            mList.addAll(ml.getMetadataDescendants());
        }
        CMLElements<CMLMetadata> metadatas = this.getMetadataElements();
        for (CMLMetadata m : metadatas) {
            mList.add(m);
        }
        return mList;
    }

    /**
     * gets all nested metadata descendants of arbitrary CMLElement.
     *
     * recurses through all metadataList and metadata children, adding the
     * latter to the list. Order is not preserved.
     *
     * @param parent
     *            with metadata(List) children
     * @return list of all descendants
     */
    public static List<CMLMetadata> getMetadataDescendants(CMLElement parent) {
        List<CMLMetadata> mList = new ArrayList<CMLMetadata>();
        Elements metadataLists = parent
                .getChildCMLElements(CMLMetadataList.TAG);
        for (int i = 0; i < metadataLists.size(); i++) {
            CMLMetadataList metadataList = (CMLMetadataList) metadataLists
                    .get(i);
            mList.addAll(metadataList.getMetadataDescendants());
        }
        // generally metadata should be under metadataList parent
        Elements metadatas = parent.getChildCMLElements(CMLMetadata.TAG);
        for (int i = 0; i < metadatas.size(); i++) {
            mList.add((CMLMetadata) metadatas.get(i));
        }
        return mList;
    }

    /**
     * gets metadata elements with a given name. if either param is null,
     * returns empty list
     *
     * @param metadataList
     *            list to filter
     * @param name
     *            value of name attribute
     * @return filtered list
     */
    private static List<CMLMetadata> getMetadataDescendantsByName(
            List<CMLMetadata> metadataList, String name) {
        List<CMLMetadata> newMetadataList = new ArrayList<CMLMetadata>();
        if (name != null && metadataList != null) {
            for (CMLMetadata metadata : metadataList) {
                if (name.equals(metadata.getName())) {
                    newMetadataList.add(metadata);
                }
            }
        }
        return newMetadataList;
    }

    /**
     * gets metadata elements with a given name. if name is null returns empty
     * list
     *
     * @param name
     *            value of name attribute
     * @return filtered list
     */
    @SuppressWarnings("unused")
    private List<CMLMetadata> getMetadataDescendantsByName(String name) {
        return getMetadataDescendantsByName(this.getMetadataDescendants(), name);
    }

}