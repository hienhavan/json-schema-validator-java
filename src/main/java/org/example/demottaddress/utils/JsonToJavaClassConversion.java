package org.example.demottaddress.utils;

import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonToJavaClassConversion {

    public static void main(String[] args) {
        String packageName = "org.example.demottaddress.pojo";
        String basePath = "src/main/resources/validator";
        String outputPojoPath = "src/main/java";
        File inputJson = new File(basePath + File.separator + "user-schema.json");
        File outputPojoDirectory = new File(outputPojoPath);
        if (!outputPojoDirectory.exists()) {
            outputPojoDirectory.mkdirs();
        }
        try {
            new JsonToJavaClassConversion().convertJsonToJavaClass(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName) throws IOException {
        JCodeModel jcodeModel = new JCodeModel();
        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isUsePrimitives() {
                return true;
            }
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }

            @Override
            public boolean isIncludeAdditionalProperties() {
                return false;
            }

        };

        // Sử dụng SchemaMapper để tạo class Java từ JSON Schema
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

        // Xây dựng class Java trong thư mục đã chỉ định
        jcodeModel.build(outputJavaClassDirectory);
    }
}
