package util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class GeraBanco {
    public static void main(String args []) {
        AnnotationConfiguration conf = new AnnotationConfiguration();
        conf.configure("database/hibernate.cfg.xml");
        SchemaExport se = new SchemaExport(conf);
        se.drop(true, true);
        se.create(true, true);
    }

}