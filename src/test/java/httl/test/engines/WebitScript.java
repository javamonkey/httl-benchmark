package httl.test.engines;

import httl.test.Benchmark;
import httl.util.Version;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import webit.script.Engine;
import webit.script.Template;

/**
 *
 * @author Zqq
 */
public class WebitScript implements Benchmark {

    Engine engine;

    public WebitScript() {

	boolean stream = "true".equals(System.getProperty("stream"));
	Map parameters = new HashMap();
	if (!stream) {
	    parameters.put("webit.script.Engine.textStatmentFactoryClass", "webit.script.core.text.impl.StringTextStatmentFactory");
	}

	engine = Engine.getEngine(null, parameters);
    }

    public String getVersion() {
	return Version.getVersion(Engine.class, "0.0.0");
    }

    public void execute(int times, String name, Map<String, Object> context, Object out) throws Exception {
	name += ".wtl";
	Engine _engine = engine;
	Template template = _engine.getTemplate(name);
	if (out instanceof OutputStream) {
	    for (int i = times; i >= 0; i--) {
		template.merge(context, (OutputStream) out);
	    }
	} else {
	    for (int i = times; i >= 0; i--) {
		template.merge(context, (Writer) out);
	    }
	}

    }
}
