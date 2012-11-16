package kr.uoscs.errclipse;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.debug.internal.ui.views.console.ProcessConsole;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.part.ViewPart;

import com.errclipse.rmi.RmiMethods;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.Solution;

public class HelperView extends ViewPart {
	private Process ps;
	private Button exe4;
	public static Text text1;
	private String language, library, method, error;
	private String result1, result2, result3;
	private Text text2,text3,text4;
	private Label t1, t2, t3, t4;
	private ConsolePlugin plugin;
	private IConsoleManager conMan;
	private IConsole[] existing;
	private ProcessConsole pConsole;
	private Action mGetSolAction;
	private Action mGoogleSearchAction;
	private Table table;
	private Image sendImg;
	private Composite top;
	private ImageLoader loader;
	
	public HelperView() {
		
	}

	public void createPartControl(Composite parent) {
		loader = ImageLoader.getILoader();
		top = new Composite(parent, SWT.BORDER);
		
		top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 5;
		layout.numColumns = 1; // number of columns
		layout.verticalSpacing = 10;
		top.setLayout(layout);
		
		Group infoGroup = new Group(top, SWT.NULL);
		infoGroup.setText("Error Info");
		GridLayout groupLayout = new GridLayout();
		groupLayout.marginHeight = 5;
		groupLayout.marginWidth = 5;
		groupLayout.numColumns = 2;
		
		infoGroup.setLayout(groupLayout);
		
		mGetSolAction = new Action("Get Solution"){
			public void run(){
				table.removeAll();
				language = "java";
				library = "test";
				method = "test_mthod";
				error = "test_erro4";
				
				String s = "";
				
				plugin = ConsolePlugin.getDefault();
				conMan = plugin.getConsoleManager();
				existing = conMan.getConsoles();
				pConsole = (ProcessConsole) existing[0];
				
				s = pConsole.getDocument().get();
				
				String regex1 = "at\\s([a-zA-Z0-9]*\\.)*";
				Pattern p1 = Pattern.compile(regex1);
				Matcher m1 = p1.matcher(s);
				result1 = "";
				while(m1.find()){
					String mg = m1.group();
					StringBuilder sb = new StringBuilder(mg);
					sb.delete(0,3);
					sb.deleteCharAt(sb.length()-1);
					result1= sb.toString()+"\n";
					break;
				}
				
				String regex2 = "\\.[a-zA-Z0-9]*\\([a-zA-Z0-9]*\\.java\\:[0-9]*\\)";
				Pattern p2 = Pattern.compile(regex2);
				Matcher m2 = p2.matcher(s);
				result2 = "";
				while(m2.find()){
					String mg = m2.group();
					StringBuilder sb = new StringBuilder(mg);
					sb.delete(sb.indexOf("("), sb.indexOf(")")+1);
					sb.deleteCharAt(0);
					result2= sb.toString()+"\n";
					break;
				}
				
				String regex3 = "java\\.[a-zA-Z]*\\.[a-zA-Z]*Exception";
				Pattern p3 = Pattern.compile(regex3);
				Matcher m3 = p3.matcher(s);
				result3 = "";
				while(m3.find()){
					if(m3.group().equals("java.lang.Exception")){
						
					}else{
						result3 = m3.group();
					}
				}

				library = result1;
				method = result2;
				error = result3;
			
				text1.setText(error);
				text2.setText(library);
				text3.setText(method);
				
				// get Solution from rmi
				List<Solution> rr = null;
				try {
					rr = RmiMethods.searchSolution(language, library, method, error);
				} catch (Exception e) {
					/*String key = "";
					key = RmiMethods.getSolutionLevelKey(language, library, method, error);
					System.out.println(key);
					RmiMethods.insertNewSolution(key, "inserted solution");*/
				}
				
				try{
					for(int i=0; i<rr.size();i++){
						Solution r = rr.get(i);
						TableItem item = new TableItem(table, SWT.NULL);
						item.setText(r.getDecs());
					}
				}catch(NullPointerException e){
					text1.setText("no return");
				}
				
			}
		};
		
		mGetSolAction.setToolTipText("Get Solution");
		mGetSolAction.setImageDescriptor(loader.loadDescriptor("sol.png"));
		
		mGoogleSearchAction = new Action("Google Search"){
			public void run(){
				if(!text1.getText().equals("")){
					String searchString = "java " + text2.getText() + " " + text3.getText() + " " + text1.getText();
 					
					GoogleQuery gq = new GoogleQuery();
					List<GoogleResult> res = gq.query(searchString , 8, 0);
					table.removeAll();
					for(int i = 0 ; i < res.size() ; i++)
					{
						TableItem item = new TableItem(table, SWT.NULL);
						item.setText(new String[]{res.get(i).getUrl()});
					}
				}
			}
		};
		
		mGoogleSearchAction.setToolTipText("Google Search");
		mGoogleSearchAction.setImageDescriptor(loader.loadDescriptor("google.png"));
		
		IActionBars actionBars = getViewSite().getActionBars();
	    IToolBarManager toolBarManager = actionBars.getToolBarManager();
        toolBarManager.removeAll();
        toolBarManager.add(mGetSolAction);
        toolBarManager.add(new Separator());
        toolBarManager.add(mGoogleSearchAction);
        
		ps = null;
		t1 = new Label(infoGroup, SWT.LEFT);
		text1 = new Text(infoGroup, SWT.SINGLE | SWT.BORDER);
		t2 = new Label(infoGroup, SWT.LEFT);
		text2 = new Text(infoGroup, SWT.SINGLE | SWT.BORDER);
		t3 = new Label(infoGroup, SWT.LEFT);
		text3 = new Text(infoGroup, SWT.SINGLE | SWT.BORDER);
		
		table = new Table(top, SWT.MULTI | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setSize(250, 100);
		
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 100;
		table.setLayoutData(data);
		
		String[] columnName = new String[]{"Solution"/*, "Votes"*/};
		int[] columnWidths = new int[]{200/*, 50*/};
		int[] columnAlignments = new int[]{SWT.LEFT/*, SWT.CENTER*/};
		
		for(int i = 0 ; i < columnName.length ; i++){
			TableColumn tableColumn = new TableColumn(table, columnAlignments[i]);
			tableColumn.setText(columnName[i]);
			tableColumn.setWidth(columnWidths[i]);
		}
		
		/*
		TableItem item1 = new TableItem(table, SWT.NULL);
		item1.setText(new String[]{"sdafadsfalkdsjflakjewlfijrewociaje"});
		TableItem item2 = new TableItem(table, SWT.NULL);
		item2.setText(new String[]{"http://www.daum.net"});
		TableItem item3 = new TableItem(table, SWT.NULL);
		item3.setText(new String[]{"slkdjflakjdsflkansd http://www.nate.com "});
		TableItem item4 = new TableItem(table, SWT.NULL);
		item4.setText(new String[]{"http://lol.inven.co.kr", "10"});
		TableItem item5 = new TableItem(table, SWT.NULL);
		item5.setText(new String[]{"http://fow.kr", "10"});
		TableItem item6 = new TableItem(table, SWT.NULL);
		item6.setText(new String[]{"http://fow.kr", "10"});
		TableItem item7 = new TableItem(table, SWT.NULL);
		item7.setText(new String[]{"http://fow.kr", "10"});
		TableItem item8 = new TableItem(table, SWT.NULL);
		item8.setText(new String[]{"http://fow.kr", "10"});*/
		
		table.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				
			}
			public void mouseDown(MouseEvent e) {
				
			}
			public void mouseDoubleClick(MouseEvent e) {
				TableItem[] selected = table.getSelection();
				if(selected.length > 0){
					try {
						for(int i = 0 ; i < selected.length; i++){
							if(!confirmUrl(selected[i].getText(0)).equals("dialog")){
								text4.setText(selected[i].getText());
								PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(confirmUrl(selected[i].getText(0))));
							}else{
								text4.setText(selected[i].getText());
								/*window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
								MessageDialog md = new MessageDialog(window.getShell(), "ErrClipse", loader.loadImage("sample.gif", top.getDisplay()), selected[i].getText(0), 0, new String[]{"Close"}, 0);
								md.open();*/
							}
						}
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		Composite clabel = new Composite(top, SWT.NONE);
		clabel.setLayout(new RowLayout());
		
		sendImg = loader.loadImage("send.png", top.getDisplay());
		Label imagel = new Label(clabel, SWT.LEFT);
		t4 = new Label(clabel, SWT.NONE);
		text4 = new Text(top, SWT.SINGLE | SWT.BORDER);
		exe4 = new Button(top, SWT.PUSH);
		
		t1.setText("Error Name");
		t2.setText("Class Name");
		t3.setText("Method Name");
		
		imagel.setImage(sendImg);
	    t4.setText("Send your Solution!");
		
		exe4.setText("Send");
		exe4.setBounds(25, 65, 90, 25);
		exe4.addSelectionListener(sa);

		GridData gridData = new GridData();
		gridData = new GridData();
	    gridData.horizontalAlignment = GridData.FILL;
	    gridData.grabExcessHorizontalSpace = true;
	    text1.setLayoutData(gridData);
	    text2.setLayoutData(gridData);
	    text3.setLayoutData(gridData);
	    
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    exe4.setLayoutData(gridData);
	    
	    gridData = new GridData();
	    gridData.horizontalAlignment = GridData.FILL;
	    gridData.verticalAlignment = GridData.FILL;
	    gridData.grabExcessVerticalSpace = true;
	    gridData.grabExcessHorizontalSpace = true;
	    text4.setLayoutData(gridData);
	    
	    text4.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {text4.selectAll();}
			public void mouseDoubleClick(MouseEvent e) {}
		});
	}


	private SelectionAdapter sa = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			try {
				if (event.getSource() == exe4) {
					 String key = ""; key =
					 RmiMethods.getSolutionLevelKey(language, library, method, error); System.out.println(key);
					 RmiMethods.insertNewSolution(key, text4.getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	public static String confirmUrl(String text) {
        String pattern = "(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w\\.-]*)*\\/?";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);
        if(matcher.find())
        	return matcher.group();
        else
        	return "dialog";
    }
	
	@Override
	public void setFocus() {
		
	}
}