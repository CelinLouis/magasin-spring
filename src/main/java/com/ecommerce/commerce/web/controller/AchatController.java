package com.ecommerce.commerce.web.controller;

import com.ecommerce.commerce.dao.AchatDao;
import com.ecommerce.commerce.dao.ProduitDao;
import com.ecommerce.commerce.dao.StatistiqueDao;
import com.ecommerce.commerce.model.Achat;
import com.ecommerce.commerce.model.Produit;
import com.ecommerce.commerce.model.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AchatController {

    @Autowired
     AchatDao achatDao;
    @Autowired
     ProduitDao produitDao;
    @Autowired
    StatistiqueDao statistiqueDao;

    @RequestMapping(value = "/achat/{date}",method = RequestMethod.GET)
    public ResponseEntity<Object> listeAchat(@PathVariable String date){
        List<Map<String,Object>> res = new ArrayList<>();
        List<Achat> p = achatDao.findByDate(date);
        p.forEach((produit -> {
            res.add(produit.toMap());
        }));
        return new ResponseEntity<Object>(res, HttpStatus.OK);

    }
    @RequestMapping(value = "/achat/statistique",method = RequestMethod.GET)
    public  ResponseEntity<Object> listeStats(){
        List<Map<String,Object>> res = new ArrayList<>();
        List<Statistique> l = statistiqueDao.getStatistique();
        l.forEach((s)->{
            res.add(s.toMap());

        });
        return  new ResponseEntity<Object>(res,HttpStatus.OK);
    }
   /* @RequestMapping(value = "/achat/{id}", method = RequestMethod.GET)
    public Achat afficheAchat(@PathVariable int id) {

        return achatDao.findById(id);
    }*/
    @DeleteMapping(value = "/achat")
    public ResponseEntity<Object> supprimerAchat(@RequestBody Achat achat){
        //achatDao.deleteAll();
        Achat achat1 = achatDao.findById(achat.getId_achat());
        if(achat1 != null){
            Produit p = achat1.getProduit().iterator().next();
            p.setQuantite(p.getQuantite()+achat1.getQuantite());
            produitDao.save(p);
            achatDao.delete(achat);
            return   ResponseEntity.ok("deleted successfully");
        }else {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping (value = "/achat")
    public ResponseEntity<Object> updateAchat(@RequestBody Achat achat) {
        //verify product
        Map<String,Object> res = new HashMap<>();
        Achat achat1 = achatDao.findById(achat.getId_achat());
        if(achat1 != null){

            Produit prod = achat.getProduit().iterator().next();
            Produit prod1 = achat1.getProduit().iterator().next();
            if((prod.getId() == prod1.getId()) && achat.getDate().equalsIgnoreCase(achat1.getDate())){
                //check stock
                int stockOld = achat1.getQuantite();
                int stockNew = achat.getQuantite();
                int difference = 0;
                boolean increment = false;
                boolean noChange = true;
                if(stockNew != stockOld){
                    difference = Math.abs(stockNew-stockOld);
                    if(stockNew < stockOld){
                        increment = false;
                    }else{
                        increment = true;
                    }
                    noChange = false;
                }else{
                    noChange = true;
                    difference = stockNew ;
                }
                if(achat1.getProduit().iterator().next().getQuantite() < difference){
                    res.put("message","stock insuffisant");
                    res.put("hasError",true);
                }else{
                    if(increment && !noChange){
                        System.out.println("moins le quantite satria stockNew kely noh ny taloha");
                        prod.setQuantite(prod1.getQuantite() - difference);
                    }else if(!increment && !noChange){
                        prod.setQuantite(prod1.getQuantite()+difference);
                        System.out.println("plus le quantite satria stockNew lehibe noh ny taloha");
                    }else{
                        System.out.println(stockNew);
                        System.out.println(stockOld);
                        System.out.println("tsy miova");
                        //prod.setQuantite(prod.getQuantite());
                        prod = prod1;
                    }
                    produitDao.save(prod);
                    Achat p = achatDao.save(achat);

                    res.put("data",p);
                    res.put("hasError",false);
                }
            }else{
                prod.setQuantite(prod.getQuantite()-achat.getQuantite());
                produitDao.save(prod);
                Achat p = achatDao.save(achat);
                res.put("data",p);
                res.put("hasError",false);
            }


                return   ResponseEntity.ok().body(res);

        }else {
            return  ResponseEntity.notFound().build();
        }


    }
    @PostMapping(value = "/achat")
    public ResponseEntity<Object> ajouterAchat(@RequestBody Achat achat1){
        //Produit p = new Produit(Integer.parseInt(produit.get("id").toString()),produit.get("nom").toString(),Integer.parseInt(produit.get("prix").toString()),Integer.parseInt(produit.get("prixAchat").toString()));
        //ystem.out.println(achat1.getProduit());

        Set<Produit> produit = achat1.getProduit();
        float t = 0;
        //int idProd = 0;
        Produit check =null;
        for(Produit p: produit){
            t += p.getPrix();
            //idProd = p.getId();
            check = p ;
        }

        if(check != null){

            Map<String,Object> res = new HashMap<>();
            if(check.getQuantite()< achat1.getQuantite()){
                res.put("hasError",true);
                res.put("message","Stock insuffisant");
                return  ResponseEntity.ok().body(res);
            }else{
                t = t*achat1.getQuantite();
                achat1.setTotal(t);
                Achat achat = null;
                Collection<Achat> lAchat = checkExist(achat1) ;
                int qteAchat1 = achat1.getQuantite();
                if(lAchat.size() > 0){//exist
                    Achat old = lAchat.iterator().next();
                    old.setQuantite(old.getQuantite()+achat1.getQuantite());
                    old.setTotal(old.getTotal()+achat1.getTotal());
                    achat1.setId_achat(old.getId_achat());
                    achat1 = old;
                }


                    check.setQuantite(check.getQuantite()-qteAchat1);
                    Produit up = check;
                    //System.out.println(check.toMap());
                    try {
                        achatDao.save(achat1);
                        produitDao.save(up);
                        System.out.println(up.toMap());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    res.put("hasError",false);
                    res.put("data",achat);
                    return  ResponseEntity.ok().body(res);

            }
        }else{
            return  ResponseEntity.noContent().build();
        }


    }

Collection<Achat> checkExist (Achat a){
        Collection<Achat> l = achatDao.findAll();
        l.removeIf((Achat b)-> {

            boolean ta = (b.getProduit().iterator().next().getId() == a.getProduit().iterator().next().getId());
            boolean tb = (a.getDate().equalsIgnoreCase(b.getDate()));
            System.out.println("date b =>"+b.getDate());
            System.out.println("date a =>"+a.getDate());
            System.out.println("id mitovy = "+ta);
            System.out.println("date mitovy = "+tb);
            System.out.println((ta && tb));
            return (!(ta && tb));});
    System.out.println("liste = >"+l.size());
        return  l ;
}
}
