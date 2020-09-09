/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import entite.Employe;
import entite.Entreprise;
import entite.Travail;
import entite.TravailPK;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.scene.chart.Axis;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import session.EmployeFacade;
import session.EntrepriseFacade;
import session.TravailFacade;

/**
 *
 * @author HASIMANANJARA
 */
@ManagedBean
@SessionScoped
public class TravailMBean implements Serializable{
    @EJB
    private TravailFacade travailFacade;
    @EJB
    private EntrepriseFacade entrepriseFacade;
    @EJB
    private EmployeFacade employeFacade;
    private Travail travail;
    private TravailPK travailPk;
    private Travail travail1;
    private TravailPK travailPk1;
    private Employe emp ;
    private Entreprise entr;
    private BarChartModel barModel;
    private PieChartModel pieModel;
    private CartesianChartModel categoryModel;

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CartesianChartModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public TravailMBean ()
    {
        travail = new Travail();
        travailPk = new TravailPK();
        travail1 = new Travail();
        travailPk1 = new TravailPK();
        emp = new Employe();
        entr = new Entreprise() ; 
        //
        pieModel = new PieChartModel();
        pieModel.set("",0);
        categoryModel =new CartesianChartModel();
        ChartSeries employe = new ChartSeries();
        employe.set("", 0);
        categoryModel.addSeries(employe);
        
    }

    public Travail getTravail() {
        return travail;
    }

    public void setTravail(Travail travail) {
        this.travail = travail;
    }

    public TravailPK getTravailPk() {
        return travailPk;
    }

    public void setTravailPk(TravailPK travailPk) {
        this.travailPk = travailPk;
    }

    public Travail getTravail1() {
        return travail1;
    }

    public void setTravail1(Travail travail1) {
        this.travail1 = travail1;
    }

    public TravailPK getTravailPk1() {
        return travailPk1;
    }

    public void setTravailPk1(TravailPK travailPk1) {
        this.travailPk1 = travailPk1;
    }

    public Employe getEmp() {
        return emp;
    }

    public void setEmp(Employe emp) {
        this.emp = emp;
    }

    public Entreprise getEntr() {
        return entr;
    }

    public void setEntr(Entreprise entr) {
        this.entr = entr;
    }
    //autocomplete pour le champ numEmploye
     public List<Employe> completeText(String query) {
         List<Employe> results;
        results = employeFacade.listNum(query);
         
        
        return results;
    }
     //autocomplete pour le champ numEntreprise
     public List<Entreprise> completeT(String query) {
        List<Entreprise> res;
        res = entrepriseFacade.listeNumEntreprise(query);
         
        
        return res;
    }
      public void afficheNotification(String summary) //pour afficher un message de notification
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }
     public void enrPersonnel ()
     {  
        List<Travail> list = new ArrayList<>();
        travail.setTRAVAInbheures(0);//initialiser au moment d creation a zero
        travailPk.setDateT(new Date());//date aujourd'hui
        travail.setTravailPK(travailPk);
        Employe emp = employeFacade.find(travail.getTravailPK().getNumEmploye());
        Entreprise entr = entrepriseFacade.find(travail.getTravailPK().getENTREnum());
        travail.setEmploye(emp);
        travail.setEntreprise(entr);
        if(!travail.getTravailPK().getENTREnum().isEmpty())list = travailFacade.listFindByNumEntrEmp(travail.getTravailPK().getENTREnum(),travail.getTravailPK().getNumEmploye());
        if(travail.getTRAVAIposte().isEmpty())afficheNotification("Vérifier les champs obligatoires");
        else if(!list.isEmpty())afficheNotification("Cette personne est deja inscrit dans cet entreprise");
        else{
        
        travailFacade.create(travail);
       
        afficheNotification("Enregistrement Effectue");
        initialiser();
        }
  
      }
    
     public void initialiser()
     {
        travail = new Travail();
        travailPk = new TravailPK();
        
     }
    public List<Travail> listeTravail ()
    {
      return travailFacade.listNoPoste();
    }
    public  void supprimerPersonnel(Travail travail)
    {
       travailFacade.remove(travail);
       afficheNotification("Suppresion effectue");
    }
    public void Affecter(Travail travail)
    {
       this.travail = travail ;
      // System.out.println("Res :"+travail.getTRAVAInbheures());
    }
    public void AffecterEntreprise (Entreprise entreprise)
    {
        this.entr = entreprise ;
    }
    public void ajoutHeure()
    {
        
       travailPk1.setENTREnum(travail.getTravailPK().getENTREnum());
       travailPk1.setNumEmploye(travail.getTravailPK().getNumEmploye());
       travail1.setTravailPK(travailPk1);
       
       travail1.setTRAVAIembauche(travail.getTRAVAIembauche());
       travail1.setTRAVAItauxhoraire(travail.getTRAVAItauxhoraire());
         //System.out.println("Res :"+);
        travailFacade.create(travail1);
        travail1 = new Travail();
        travailPk1 = new TravailPK();
        afficheNotification("Succes");
    }
     public List <Object[]> listePoint (String entreNum)
    {
      
      return travailFacade.listeEmpEntreprise(entreNum);
      
    }
     public List <Object[]> listeEmpEntrepriseAnnuel (String entreNum,int Annee)
    {
      
      return travailFacade.listeEmpEntrepriseAnnuel(entreNum, Annee);
      
    }
     public List<Object[]> listeEmpEntreprise2Date(String entreNum,Date debut,Date fin)
     {
       return travailFacade.listeEmpEntreprise2Date(entreNum, debut, fin);
     }
      public String genererExcel()
    {
         //1. Créer un Document vide
       XSSFWorkbook wb = new XSSFWorkbook();
       //2. Créer une Feuille de calcul vide
       Sheet feuille = wb.createSheet("new sheet");
       
       Entreprise ent;
       ent = entrepriseFacade.find(travailPk.getENTREnum());
        
        Row row = feuille.createRow((short)0);
        row.createCell(0).setCellValue("Bilan d'une Entreprise");
        row = feuille.createRow((short)1); 
        row.createCell(0).setCellValue(ent.getENTREdesign());
        row = feuille.createRow((short)2); 
        row.createCell(0).setCellValue("Siege :"+ent.getENTRElieu());
       
        
       
        row = feuille.createRow((short)4);     
        row.createCell(0).setCellValue("Numero");
        row.createCell(1).setCellValue("Nom");
        row.createCell(2).setCellValue("Prenom");
        row.createCell(3).setCellValue("Nb jours");
        row.createCell(4).setCellValue("Salaire");
       
       
       
       List<Object []> lister = travailFacade.listeEmpEntreprise2Date(travailPk.getENTREnum(),travail.getDebut(), travail.getFin());
       
       int i = 0;
      for(Iterator it = lister.iterator(); it.hasNext();i++) {
            
          Object[] test = (Object[]) it.next();
            
            row = feuille.createRow((short)i+5);
            
           
           
            row.createCell(0).setCellValue(test[0].toString());
            row.createCell(1).setCellValue(test[1].toString());
            row.createCell(2).setCellValue(test[2].toString());
            row.createCell(3).setCellValue(test[3].toString());
            row.createCell(4).setCellValue(test[4].toString());
            
         
           
            
            
           
           
       }
       FileOutputStream fileOut;
       try 
       {
         String nomFichier = "E:\\Excel\\bilan.xlsx";
         fileOut = new FileOutputStream(nomFichier);
         wb.write(fileOut);
         fileOut.close();
       } catch (FileNotFoundException e) {e.printStackTrace();} 
         catch (IOException e) {e.printStackTrace();}
       
       return "bilanEntreprise2DateInterf.xhtml";
    }
     public List<Travail>listeEntreEmp(String num,Date debutContrat,Date finContrat)
     {
       return travailFacade.listeEntrepriseEmp(num, debutContrat, finContrat);
     }
     
    public void transfertDonnee()
    {
        
        System.out.println("RES : "+travailPk.getENTREnum());
        
        int test = travailPk.getDateT().getYear();
        int somme = 1900+test;
        travail.setAnnee(somme);
        travail.getAnnee().toString();
        System.out.println("Res"+travail.getAnnee().toString());
        
        
      
       
    }
    public void transfert2()
    {
           System.out.println("Test");
    }
    public void transfert3()
    {
    
    }
   //
    
    
    public void test ()
    {
       pieModel = new PieChartModel();
       categoryModel = new CartesianChartModel();
       ChartSeries employe = new ChartSeries();
       List<Object []> lister = travailFacade.listeEmpEntreprise2Date(travailPk.getENTREnum(),travail.getDebut(), travail.getFin());
       if(lister != null)
       {   for (Object[] pie : lister)
       {   pieModel.set((String) pie[0],(Number) pie[4]);
           employe.set((String) pie[0],(Number) pie[4]);
       }
       categoryModel.addSeries(employe);
       }
    }

    
}
