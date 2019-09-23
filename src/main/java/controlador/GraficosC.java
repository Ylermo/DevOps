package controlador;

import dao.Graficos;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import modelo.Contar;
import modelo.Cultivo;
import modelo.Informacion;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

@Named(value = "graficosC")
@SessionScoped
public class GraficosC implements Serializable {

    private PieChartModel pieModel;
    Graficos dao;
    private BarChartModel barModel;
    List<Informacion> lista;
    private List<Contar> contar;
    private LineChartModel lineModel;

    public void lista() throws Exception {
        dao = new Graficos();
        List<Cultivo> listacul;
        List<Informacion> listado;
        try {
            Contarlista();
            listacul = dao.listar();
            listado = dao.listargraf();
            graficar(listacul);
            createLineModel(listado);
        } catch (Exception e) {
            throw e;
        }
    }

    /*Pie model*/
    public void graficar(List<Cultivo> lista) {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();

        List<Number> values = new ArrayList<>();
        lista.forEach((cm) -> {
            values.add(cm.getTIP());
        });
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        lista.forEach((lbl) -> {
            labels.add(lbl.getTIPCUL());
        });
        data.setLabels(labels);

        pieModel.setData(data);
    }

    public void Contarlista() {
        try {
            contar = dao.listarContar();
        } catch (Exception e) {
        }
    }


    public void createLineModel(List<Informacion> lista) {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        
        List<Number> values = new ArrayList<>();
        lista.forEach((info) -> {
            values.add(info.getVERDMES());
        });
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Suma de cosecha en general");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        lista.forEach((lbl) -> {
            labels.add(String.valueOf(lbl.getFECINFO()));
        });
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public Graficos getDao() {
        return dao;
    }

    public void setDao(Graficos dao) {
        this.dao = dao;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<Contar> getContar() {
        return contar;
    }

    public void setContar(List<Contar> contar) {
        this.contar = contar;
    }

    public List<Informacion> getLista() {
        return lista;
    }

    public void setLista(List<Informacion> lista) {
        this.lista = lista;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    
}
