package cn.lw.yuanbaoapi;

import android.support.design.widget.CollapsingToolbarLayout;

import org.junit.runners.model.InitializationError;
import org.robolectric.RoboSettings;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;

/**
 * Created by lw on 2017/7/2.
 * 第一次配置Robolectric需要下载很多东西,重新配置阿里云的下载路径加快下载速度
 */
public class MyRobolectricTestRunner extends RobolectricTestRunner {
    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
     * and res directory by default. Use the  annotation to configure.
     *
     * @param testClass the test class to be run
     * @throws InitializationError if junit says so
     */
    public MyRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);

        // 从源码知道MavenDependencyResolver默认以RoboSettings的repositoryUrl和repositoryId为默认值，因此只需要对RoboSetting进行赋值即可
        RoboSettings.setMavenRepositoryId("alimaven");
        RoboSettings.setMavenRepositoryUrl("http://maven.aliyun.com/nexus/content/groups/public/");
    }

    public InstrumentationConfiguration createClassLoaderConfig() {
        InstrumentationConfiguration.Builder builder = InstrumentationConfiguration.newBuilder();
        builder.addInstrumentedPackage(CollapsingToolbarLayout.class.getName());
        return builder.build();
    }

}
