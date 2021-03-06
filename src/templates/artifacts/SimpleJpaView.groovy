package ${g.targetPackageName}

import javax.swing.JOptionPane
import static ca.odell.glazedlists.gui.AbstractTableComparatorChooser.*
import static javax.swing.SwingConstants.*
import net.miginfocom.swing.MigLayout
import org.joda.time.*
import java.awt.*
import org.jdesktop.swingx.prompt.PromptSupport

actions {
	action(id: 'search', name: app.getMessage('simplejpa.search.label'), closure: controller.search)
	action(id: 'save', name: app.getMessage('simplejpa.dialog.save.button'), closure: controller.save)
	action(id: 'cancel', name: app.getMessage("simplejpa.dialog.cancel.button"), closure: controller.clear)
	action(id: 'delete', name: app.getMessage("simplejpa.dialog.delete.button"), closure: controller.delete)
${g.actions(1)}}

panel(id: 'mainPanel') {
    borderLayout()

    panel(constraints: PAGE_START) {
        flowLayout(alignment: FlowLayout.LEADING)
        textField(id: '${g.firstAttrSearch}', columns: 20, text: bind('${g.firstAttrSearch}', target: model, mutual: true), action: search)
        button(action: search)
    }

    scrollPane(constraints: CENTER) {
        glazedTable(id: 'table', list: model.${g.domainClassGlazedListVariable}, sortingStrategy: SINGLE_COLUMN, onValueChanged: controller.tableSelectionChanged${g.tableActions()}) {
${g.table(4)}
        }
    }

    panel(id: "form", layout: new MigLayout('hidemode 2', '[right][left][left,grow]',''), constraints: PAGE_END, focusCycleRoot: true) {
${g.dataEntry(3)}
        panel(constraints: 'span, growx, wrap') {
            flowLayout(alignment: FlowLayout.LEADING)
            button(action: save)
            button(visible: bind{table.isRowSelected}, action: cancel)
            button(visible: bind{table.isRowSelected}, action: delete)
        }
    }
}

PromptSupport.setPrompt("${g.firstAttrAsNatural} Search", ${g.firstAttrSearch})