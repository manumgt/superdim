package mobi.pruss.superdim;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AddShortcut extends Activity {
	public final static String LOAD_CUSTOM="loadCustom";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		Log.v("AddShortcut", i.getAction());
		Log.v("AddShortcut", i.getComponent().getShortClassName());
		
		if (i.getAction().equals(Intent.ACTION_CREATE_SHORTCUT) && 
			i.getComponent() != null) {
				String shortClass = i.getComponent().getShortClassName();
			if (shortClass.endsWith(".CreateShortcut0"))
				addShortcut(0, "Night 1", R.drawable.custom0);
			else if (shortClass.endsWith(".CreateShortcut1"))
				addShortcut(1, "Night 2", R.drawable.custom1);
			else if (shortClass.endsWith(".CreateShortcut2"))
				addShortcut(2, "Night 3", R.drawable.custom2);
			else if (shortClass.endsWith(".CreateShortcut3"))
				addShortcut(3, "Day 1", R.drawable.custom3);
			else if (shortClass.endsWith(".CreateShortcut4"))
				addShortcut(4, "Day 2", R.drawable.custom4);
		    finish();
		    return;
		}
		addShortcut(0, "hello", R.drawable.custom0);
		finish();
	}

	void addShortcut(int customNumber, String name, int icon) {
		Log.v("AddShortcut", name);
		Intent si = new Intent(this, SuperDim.class);
		si.putExtra(LOAD_CUSTOM, customNumber);
		si.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

		Intent i = new Intent();
		i.putExtra(Intent.EXTRA_SHORTCUT_INTENT, si);
		i.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
		i.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, 
				Intent.ShortcutIconResource.fromContext(this, icon));
		setResult(RESULT_OK, i);
	}
}
