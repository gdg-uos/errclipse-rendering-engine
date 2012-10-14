package kr.uoscs.errclipse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.errclipse.rmi.RmiMethods;
import com.errclipse.rmi.interfaces.GGeneralErrorClazz.Solution;

public class HelperView extends ViewPart {
	private Process ps;
	private Button exe4;
	private Button exe5;
	public static Text text1;
	private String language, library, method, error;
	private String result1, result2, result3;
	private org.eclipse.swt.widgets.List list1;
	private Text text2,text3,text4;
	
	public HelperView() {

	}

	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.BORDER);
		
		top.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 5;

		layout.makeColumnsEqualWidth = true;// make each column have same width
		layout.numColumns = 2; // number of columns
		layout.verticalSpacing = 10;
		top.setLayout(layout);
		
		ps = null;
		exe5 = new Button(top, SWT.PUSH);
		text1 = new Text(top, SWT.SINGLE | SWT.WRAP);
		text2 = new Text(top, SWT.SINGLE | SWT.WRAP);
		text3 = new Text(top, SWT.SINGLE | SWT.WRAP);
		list1 = new org.eclipse.swt.widgets.List(top, SWT.SINGLE);
		text4 = new Text(top, SWT.MULTI | SWT.WRAP);
		exe4 = new Button(top, SWT.PUSH);
		
		text1.setText("test");
		text1.setEditable(false);
		text2.setText("Method Name");
		text3.setText("Error Name");
		text2.setEditable(false);
		text3.setEditable(false);
		
		exe4.setText("Send");
		exe5.setText("getError");
		exe4.setBounds(25, 65, 90, 25);
		exe5.setBounds(25, 105, 90, 25);
		exe4.addSelectionListener(sa);
		exe5.addSelectionListener(sa);
		list1.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				
			}
			public void mouseDown(MouseEvent e) {
				
			}
			public void mouseDoubleClick(MouseEvent e) {
				String[] selected = list1.getSelection();
				if(selected.length > 0){
					try {
						PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(selected[0]));
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

		GridData gridData = new GridData();
		// here like the banner, text is added to "top".
		text1.setText("Click get Error Button!");
		gridData = new GridData();
	    gridData.horizontalAlignment = GridData.FILL;
	    text1.setLayoutData(gridData);
	    
	    gridData = new GridData();
	    gridData.horizontalAlignment = GridData.CENTER;
	    exe5.setLayoutData(gridData);
	    
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    exe4.setLayoutData(gridData);
	    
	    gridData = new GridData();
	    text4.setText("Send your Solution!");
	    gridData.horizontalAlignment = GridData.FILL;
	    gridData.verticalAlignment = GridData.FILL;
	    gridData.grabExcessVerticalSpace = true;
	    gridData.grabExcessHorizontalSpace = true;
	    text4.setLayoutData(gridData);
	    
	    gridData = new GridData();
	    gridData.horizontalAlignment = GridData.FILL;
	    gridData.verticalAlignment = GridData.FILL;
	    gridData.horizontalSpan = 2;
	    gridData.grabExcessVerticalSpace = true;
	    gridData.grabExcessHorizontalSpace = true;
	    list1.setLayoutData(gridData);
	}


	private SelectionAdapter sa = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			try {
				if (event.getSource() == exe4) {
					String key = "";
					key = RmiMethods.getSolutionLevelKey(language, library, method, error);
					System.out.println(key);
					RmiMethods.insertNewSolution(key, text4.getText());
				} else if (event.getSource() == exe5) {
					list1.removeAll();
					language = "java";
					library = "test";
					method = "test_mthod";
					error = "test_erro4";
					
					Location location = Platform.getInstanceLocation();
					String logpath = location.getURL().getPath()+".metadata/errclipse_log";
					StringBuffer s = new StringBuffer();
					try{
						File file = new File(logpath);
						InputStream is = new FileInputStream(file);
						int c;
						while((c = is.read()) != -1)
							s.append((char)c);
						is.close();
					}catch(Exception e){
						System.out.println(e.toString());
					}
					
					String regex1 = "at\\s([a-zA-Z0-9]*\\.)*";
					Pattern p1 = Pattern.compile(regex1);
					Matcher m1 = p1.matcher(s.toString());
					result1 = "";
					while(m1.find()){
						String mg = m1.group();
						StringBuilder sb = new StringBuilder(mg);
						sb.delete(0,2);
						sb.deleteCharAt(sb.length()-1);
						result1= sb.toString()+"\n";
						break;
					}
					
					String regex2 = "\\.[a-zA-Z0-9]*\\([a-zA-Z0-9]*\\.java\\:[0-9]*\\)";
					Pattern p2 = Pattern.compile(regex2);
					Matcher m2 = p2.matcher(s.toString());
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
					Matcher m3 = p3.matcher(s.toString());
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
							list1.add(r.getDecs(), i);
						}
						text2.setText("Method Name : "+method);
						text3.setText("Error Name : "+error);
					}catch(NullPointerException e){
						text1.setText("no return");
						text2.setText("Method Name : "+method);
						text3.setText("Error Name : "+error);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	@Override
	public void setFocus() {
		
	}
}