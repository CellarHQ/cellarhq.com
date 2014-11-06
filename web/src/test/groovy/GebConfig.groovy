import org.openqa.selenium.Capabilities
import org.openqa.selenium.Dimension
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities

reportsDir = 'build/geb-reports'
//driver = {
//    Capabilities caps = DesiredCapabilities.phantomjs()
//    def phantomJsDriver = new PhantomJSDriver(PhantomJSDriverService.createDefaultService(caps), caps)
//    phantomJsDriver.manage().window().setSize(new Dimension(1028, 768))
//
//    return phantomJsDriver
//}
