package code.jasper.reports.util;

public enum ReportFormat {

    PDF ("pdf"),
    EXCEL("xlsx"),
    DOCX("docx");

    private String extension;

    ReportFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}
