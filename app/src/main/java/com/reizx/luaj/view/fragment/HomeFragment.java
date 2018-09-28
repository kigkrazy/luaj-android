package com.reizx.luaj.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ResourceUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.luaj.R;
import com.reizx.luaj.component.hyperbolic;
import com.reizx.luaj.contract.HomeConstract;
import com.reizx.luaj.presenter.HomePresenter;
import com.reizx.luaj.util.LuajUtil;
import com.reizx.luaj.view.common.BaseFragment;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.Bit32Lib;
import org.luaj.vm2.lib.CoroutineLib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.TableLib;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JseIoLib;
import org.luaj.vm2.lib.jse.JseMathLib;
import org.luaj.vm2.lib.jse.JseOsLib;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.luaj.vm2.lib.jse.LuajavaLib;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeConstract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    private Globals globals;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globals = JsePlatform.standardGlobals();
    }

    @OnClick(R.id.btn_app_invoke_file)
    public void invokeFile() {
        String path = "/sdcard/SimpleExample.lua";
        // copy the script to path
        ResourceUtils.copyFileFromAssets("SimpleExample.lua", path);
        // init global before
        // create an environment to run in
        // Globals globals = JsePlatform.standardGlobals();
        // Use the convenience function on Globals to load a chunk.
        LuaValue chunk = globals.loadfile(path);
        // Use any of the "call()" or "invoke()" functions directly on the chunk.
        chunk.invoke();
    }

    @OnClick(R.id.btn_app_invoke_stream)
    public void invokeStream() {
        // get the script InputStream
        InputStream in = new ByteArrayInputStream(ResourceUtils.readAssets2String("SimpleExample.lua").getBytes());
        // init global before
        // create an environment to run in
        // Globals globals = JsePlatform.standardGlobals();
        // Use the convenience function on Globals to load a chunk.
        /** Load the content form an input stream as a binary chunk or text file.
         * @param is InputStream containing a lua script or compiled lua"
         * @param chunkname Name that will be used within the chunk as the source.
         * @param mode String containing 'b' or 't' or both to control loading as binary or text or either.
         * @param environment LuaTable to be used as the environment for the loaded function.
         * */
        LuaValue chunk = globals.load(in, "@"+"Simple", "bt", globals);
        // Use any of the "call()" or "invoke()" functions directly on the chunk.
        chunk.invoke();
    }

    @OnClick(R.id.btn_app_custom_luaj_env)
    public void invokeCustom(){
        Globals globals = customEvn();

        // get the script InputStream
        InputStream in = new ByteArrayInputStream(ResourceUtils.readAssets2String("hyperbolicapp.lua").getBytes());
        // init global before
        // create an environment to run in
        // Globals globals = JsePlatform.standardGlobals();
        // Use the convenience function on Globals to load a chunk.
        /** Load the content form an input stream as a binary chunk or text file.
         * @param is InputStream containing a lua script or compiled lua"
         * @param chunkname Name that will be used within the chunk as the source.
         * @param mode String containing 'b' or 't' or both to control loading as binary or text or either.
         * @param environment LuaTable to be used as the environment for the loaded function.
         * */
        LuaValue chunk = globals.load(in, "@"+"hyperbolicapp", "bt", globals);
        // Use any of the "call()" or "invoke()" functions directly on the chunk.
        chunk.invoke();
    }

    /**
     * 自定义lua环境，可以自定义API
     * @return
     */
    public Globals customEvn(){
        Globals globals = new Globals();
        globals.load(new JseBaseLib());
        globals.load(new PackageLib());
        globals.load(new Bit32Lib());
        globals.load(new TableLib());
        globals.load(new StringLib());
        globals.load(new CoroutineLib());
        globals.load(new JseMathLib());
        globals.load(new JseIoLib());
        globals.load(new JseOsLib());
        globals.load(new LuajavaLib());
        //todo register library
        globals.load(new hyperbolic());

        LoadState.install(globals);
        LuaC.install(globals);
        return globals;
    }

    @OnClick(R.id.btn_app_list_example)
    public void testListReturn(){
        String path = "/sdcard/ListExample.lua";
        // copy the script to path
        ResourceUtils.copyFileFromAssets("ListExample.lua", path);
        LuajUtil.execFile(path);
    }



    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initAllMembersView() {
        super.initAllMembersView();
        initTopBar();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTopBar() {
        mTopBar.setTitle("主页");
    }

    @Override
    public void setCurrentIp(String ip) {
    }
}
