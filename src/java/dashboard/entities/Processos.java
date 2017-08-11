/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nlima.huufma
 */
@Entity
@Table(name = "processos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Processos.findAll", query = "SELECT p FROM Processos p"),
    @NamedQuery(name = "Processos.findBySolcod", query = "SELECT p FROM Processos p WHERE p.solcod = :solcod"),
    @NamedQuery(name = "Processos.findBySolservcod", query = "SELECT p FROM Processos p WHERE p.solservcod = :solservcod"),
    @NamedQuery(name = "Processos.findBySolserv", query = "SELECT p FROM Processos p WHERE p.solserv = :solserv"),
    @NamedQuery(name = "Processos.findBySoltipocod", query = "SELECT p FROM Processos p WHERE p.soltipocod = :soltipocod"),
    @NamedQuery(name = "Processos.findBySoltipo", query = "SELECT p FROM Processos p WHERE p.soltipo = :soltipo"),
    @NamedQuery(name = "Processos.findBySoldtabertura", query = "SELECT p FROM Processos p WHERE p.soldtabertura = :soldtabertura"),
    @NamedQuery(name = "Processos.findBySolhorabert", query = "SELECT p FROM Processos p WHERE p.solhorabert = :solhorabert"),
    @NamedQuery(name = "Processos.findBySoldtenvio", query = "SELECT p FROM Processos p WHERE p.soldtenvio = :soldtenvio"),
    @NamedQuery(name = "Processos.findBySolenvio", query = "SELECT p FROM Processos p WHERE p.solenvio = :solenvio"),
    @NamedQuery(name = "Processos.findBySolhoranevio", query = "SELECT p FROM Processos p WHERE p.solhoranevio = :solhoranevio"),
    @NamedQuery(name = "Processos.findBySolativo", query = "SELECT p FROM Processos p WHERE p.solativo = :solativo"),
    @NamedQuery(name = "Processos.findBySolpacespcod", query = "SELECT p FROM Processos p WHERE p.solpacespcod = :solpacespcod"),
    @NamedQuery(name = "Processos.findBySolpacespec", query = "SELECT p FROM Processos p WHERE p.solpacespec = :solpacespec"),
    @NamedQuery(name = "Processos.findBySolintencacod", query = "SELECT p FROM Processos p WHERE p.solintencacod = :solintencacod"),
    @NamedQuery(name = "Processos.findBySolintencami", query = "SELECT p FROM Processos p WHERE p.solintencami = :solintencami"),
    @NamedQuery(name = "Processos.findBySoldescricao", query = "SELECT p FROM Processos p WHERE p.soldescricao = :soldescricao"),
    @NamedQuery(name = "Processos.findByStepessoacod", query = "SELECT p FROM Processos p WHERE p.stepessoacod = :stepessoacod"),
    @NamedQuery(name = "Processos.findBySteativopes", query = "SELECT p FROM Processos p WHERE p.steativopes = :steativopes"),
    @NamedQuery(name = "Processos.findByStecod", query = "SELECT p FROM Processos p WHERE p.stecod = :stecod"),
    @NamedQuery(name = "Processos.findBySteativo", query = "SELECT p FROM Processos p WHERE p.steativo = :steativo"),
    @NamedQuery(name = "Processos.findByStenome", query = "SELECT p FROM Processos p WHERE p.stenome = :stenome"),
    @NamedQuery(name = "Processos.findByStesexocod", query = "SELECT p FROM Processos p WHERE p.stesexocod = :stesexocod"),
    @NamedQuery(name = "Processos.findByStesexo", query = "SELECT p FROM Processos p WHERE p.stesexo = :stesexo"),
    @NamedQuery(name = "Processos.findByStedtnasc", query = "SELECT p FROM Processos p WHERE p.stedtnasc = :stedtnasc"),
    @NamedQuery(name = "Processos.findBySteemail", query = "SELECT p FROM Processos p WHERE p.steemail = :steemail"),
    @NamedQuery(name = "Processos.findByStecpf", query = "SELECT p FROM Processos p WHERE p.stecpf = :stecpf"),
    @NamedQuery(name = "Processos.findByStecns", query = "SELECT p FROM Processos p WHERE p.stecns = :stecns"),
    @NamedQuery(name = "Processos.findBySteoutdoc", query = "SELECT p FROM Processos p WHERE p.steoutdoc = :steoutdoc"),
    @NamedQuery(name = "Processos.findByStecepres", query = "SELECT p FROM Processos p WHERE p.stecepres = :stecepres"),
    @NamedQuery(name = "Processos.findByStemunrescod", query = "SELECT p FROM Processos p WHERE p.stemunrescod = :stemunrescod"),
    @NamedQuery(name = "Processos.findByStemunres", query = "SELECT p FROM Processos p WHERE p.stemunres = :stemunres"),
    @NamedQuery(name = "Processos.findByStemunatres", query = "SELECT p FROM Processos p WHERE p.stemunatres = :stemunatres"),
    @NamedQuery(name = "Processos.findBySteufrescod", query = "SELECT p FROM Processos p WHERE p.steufrescod = :steufrescod"),
    @NamedQuery(name = "Processos.findBySteufres", query = "SELECT p FROM Processos p WHERE p.steufres = :steufres"),
    @NamedQuery(name = "Processos.findBySteufativores", query = "SELECT p FROM Processos p WHERE p.steufativores = :steufativores"),
    @NamedQuery(name = "Processos.findBySteufsigres", query = "SELECT p FROM Processos p WHERE p.steufsigres = :steufsigres"),
    @NamedQuery(name = "Processos.findBySteleingcod", query = "SELECT p FROM Processos p WHERE p.steleingcod = :steleingcod"),
    @NamedQuery(name = "Processos.findBySteleing", query = "SELECT p FROM Processos p WHERE p.steleing = :steleing"),
    @NamedQuery(name = "Processos.findBySteinglescod", query = "SELECT p FROM Processos p WHERE p.steinglescod = :steinglescod"),
    @NamedQuery(name = "Processos.findBySteingles", query = "SELECT p FROM Processos p WHERE p.steingles = :steingles"),
    @NamedQuery(name = "Processos.findBySteescolcod", query = "SELECT p FROM Processos p WHERE p.steescolcod = :steescolcod"),
    @NamedQuery(name = "Processos.findBySteescol", query = "SELECT p FROM Processos p WHERE p.steescol = :steescol"),
    @NamedQuery(name = "Processos.findBySteprofisreg", query = "SELECT p FROM Processos p WHERE p.steprofisreg = :steprofisreg"),
    @NamedQuery(name = "Processos.findBySteprofiscod", query = "SELECT p FROM Processos p WHERE p.steprofiscod = :steprofiscod"),
    @NamedQuery(name = "Processos.findBySteprofis", query = "SELECT p FROM Processos p WHERE p.steprofis = :steprofis"),
    @NamedQuery(name = "Processos.findByStehabprofis", query = "SELECT p FROM Processos p WHERE p.stehabprofis = :stehabprofis"),
    @NamedQuery(name = "Processos.findBySteespeccod", query = "SELECT p FROM Processos p WHERE p.steespeccod = :steespeccod"),
    @NamedQuery(name = "Processos.findBySteespec", query = "SELECT p FROM Processos p WHERE p.steespec = :steespec"),
    @NamedQuery(name = "Processos.findBySteanoespec", query = "SELECT p FROM Processos p WHERE p.steanoespec = :steanoespec"),
    @NamedQuery(name = "Processos.findBySteativoesp", query = "SELECT p FROM Processos p WHERE p.steativoesp = :steativoesp"),
    @NamedQuery(name = "Processos.findByStetpespcod", query = "SELECT p FROM Processos p WHERE p.stetpespcod = :stetpespcod"),
    @NamedQuery(name = "Processos.findByStetpesp", query = "SELECT p FROM Processos p WHERE p.stetpesp = :stetpesp"),
    @NamedQuery(name = "Processos.findByStefamcbocod", query = "SELECT p FROM Processos p WHERE p.stefamcbocod = :stefamcbocod"),
    @NamedQuery(name = "Processos.findByStefamcbo", query = "SELECT p FROM Processos p WHERE p.stefamcbo = :stefamcbo"),
    @NamedQuery(name = "Processos.findByStecbocod", query = "SELECT p FROM Processos p WHERE p.stecbocod = :stecbocod"),
    @NamedQuery(name = "Processos.findByStecbo", query = "SELECT p FROM Processos p WHERE p.stecbo = :stecbo"),
    @NamedQuery(name = "Processos.findBySteeqcod", query = "SELECT p FROM Processos p WHERE p.steeqcod = :steeqcod"),
    @NamedQuery(name = "Processos.findBySteequipe", query = "SELECT p FROM Processos p WHERE p.steequipe = :steequipe"),
    @NamedQuery(name = "Processos.findBySteine", query = "SELECT p FROM Processos p WHERE p.steine = :steine"),
    @NamedQuery(name = "Processos.findBySteativoeq", query = "SELECT p FROM Processos p WHERE p.steativoeq = :steativoeq"),
    @NamedQuery(name = "Processos.findByStetipoeqcod", query = "SELECT p FROM Processos p WHERE p.stetipoeqcod = :stetipoeqcod"),
    @NamedQuery(name = "Processos.findByStetipoeq", query = "SELECT p FROM Processos p WHERE p.stetipoeq = :stetipoeq"),
    @NamedQuery(name = "Processos.findByStetpativoeq", query = "SELECT p FROM Processos p WHERE p.stetpativoeq = :stetpativoeq"),
    @NamedQuery(name = "Processos.findBySteunidcod", query = "SELECT p FROM Processos p WHERE p.steunidcod = :steunidcod"),
    @NamedQuery(name = "Processos.findBySteunid", query = "SELECT p FROM Processos p WHERE p.steunid = :steunid"),
    @NamedQuery(name = "Processos.findByStecnesunid", query = "SELECT p FROM Processos p WHERE p.stecnesunid = :stecnesunid"),
    @NamedQuery(name = "Processos.findBySteativounid", query = "SELECT p FROM Processos p WHERE p.steativounid = :steativounid"),
    @NamedQuery(name = "Processos.findByStetipounidcod", query = "SELECT p FROM Processos p WHERE p.stetipounidcod = :stetipounidcod"),
    @NamedQuery(name = "Processos.findByStetipounid", query = "SELECT p FROM Processos p WHERE p.stetipounid = :stetipounid"),
    @NamedQuery(name = "Processos.findByStesetorunid", query = "SELECT p FROM Processos p WHERE p.stesetorunid = :stesetorunid"),
    @NamedQuery(name = "Processos.findByStetpativounid", query = "SELECT p FROM Processos p WHERE p.stetpativounid = :stetpativounid"),
    @NamedQuery(name = "Processos.findByStecep", query = "SELECT p FROM Processos p WHERE p.stecep = :stecep"),
    @NamedQuery(name = "Processos.findByStemuniccod", query = "SELECT p FROM Processos p WHERE p.stemuniccod = :stemuniccod"),
    @NamedQuery(name = "Processos.findByStemunic", query = "SELECT p FROM Processos p WHERE p.stemunic = :stemunic"),
    @NamedQuery(name = "Processos.findBySteuf", query = "SELECT p FROM Processos p WHERE p.steuf = :steuf"),
    @NamedQuery(name = "Processos.findBySteativouf", query = "SELECT p FROM Processos p WHERE p.steativouf = :steativouf"),
    @NamedQuery(name = "Processos.findByStesiguf", query = "SELECT p FROM Processos p WHERE p.stesiguf = :stesiguf"),
    @NamedQuery(name = "Processos.findBySteufcod", query = "SELECT p FROM Processos p WHERE p.steufcod = :steufcod"),
    @NamedQuery(name = "Processos.findBySteativomun", query = "SELECT p FROM Processos p WHERE p.steativomun = :steativomun"),
    @NamedQuery(name = "Processos.findBySteregcod", query = "SELECT p FROM Processos p WHERE p.steregcod = :steregcod"),
    @NamedQuery(name = "Processos.findByStereg", query = "SELECT p FROM Processos p WHERE p.stereg = :stereg"),
    @NamedQuery(name = "Processos.findBySteonu", query = "SELECT p FROM Processos p WHERE p.steonu = :steonu"),
    @NamedQuery(name = "Processos.findByStepais", query = "SELECT p FROM Processos p WHERE p.stepais = :stepais"),
    @NamedQuery(name = "Processos.findByStesigpais", query = "SELECT p FROM Processos p WHERE p.stesigpais = :stesigpais"),
    @NamedQuery(name = "Processos.findByStenequs", query = "SELECT p FROM Processos p WHERE p.stenequs = :stenequs"),
    @NamedQuery(name = "Processos.findBySteeqcomus", query = "SELECT p FROM Processos p WHERE p.steeqcomus = :steeqcomus"),
    @NamedQuery(name = "Processos.findBySteeqintus", query = "SELECT p FROM Processos p WHERE p.steeqintus = :steeqintus"),
    @NamedQuery(name = "Processos.findByStenwebcus", query = "SELECT p FROM Processos p WHERE p.stenwebcus = :stenwebcus"),
    @NamedQuery(name = "Processos.findByStenmicus", query = "SELECT p FROM Processos p WHERE p.stenmicus = :stenmicus"),
    @NamedQuery(name = "Processos.findByStensomus", query = "SELECT p FROM Processos p WHERE p.stensomus = :stensomus"),
    @NamedQuery(name = "Processos.findByStenfoneus", query = "SELECT p FROM Processos p WHERE p.stenfoneus = :stenfoneus"),
    @NamedQuery(name = "Processos.findBySteintadequs", query = "SELECT p FROM Processos p WHERE p.steintadequs = :steintadequs"),
    @NamedQuery(name = "Processos.findBySteinst", query = "SELECT p FROM Processos p WHERE p.steinst = :steinst"),
    @NamedQuery(name = "Processos.findBySteinstcod", query = "SELECT p FROM Processos p WHERE p.steinstcod = :steinstcod"),
    @NamedQuery(name = "Processos.findByStetipoinst", query = "SELECT p FROM Processos p WHERE p.stetipoinst = :stetipoinst"),
    @NamedQuery(name = "Processos.findByStecepinst", query = "SELECT p FROM Processos p WHERE p.stecepinst = :stecepinst"),
    @NamedQuery(name = "Processos.findByStemuninstcod", query = "SELECT p FROM Processos p WHERE p.stemuninstcod = :stemuninstcod"),
    @NamedQuery(name = "Processos.findByStemuninst", query = "SELECT p FROM Processos p WHERE p.stemuninst = :stemuninst"),
    @NamedQuery(name = "Processos.findByStemunsativoinst", query = "SELECT p FROM Processos p WHERE p.stemunsativoinst = :stemunsativoinst"),
    @NamedQuery(name = "Processos.findBySteonuinst", query = "SELECT p FROM Processos p WHERE p.steonuinst = :steonuinst"),
    @NamedQuery(name = "Processos.findByStepaisinst", query = "SELECT p FROM Processos p WHERE p.stepaisinst = :stepaisinst"),
    @NamedQuery(name = "Processos.findByStepaissiginst", query = "SELECT p FROM Processos p WHERE p.stepaissiginst = :stepaissiginst"),
    @NamedQuery(name = "Processos.findByStereginstcod", query = "SELECT p FROM Processos p WHERE p.stereginstcod = :stereginstcod"),
    @NamedQuery(name = "Processos.findByStereginst", query = "SELECT p FROM Processos p WHERE p.stereginst = :stereginst"),
    @NamedQuery(name = "Processos.findBySteufinstcod", query = "SELECT p FROM Processos p WHERE p.steufinstcod = :steufinstcod"),
    @NamedQuery(name = "Processos.findBySteufinst", query = "SELECT p FROM Processos p WHERE p.steufinst = :steufinst"),
    @NamedQuery(name = "Processos.findBySteufsiginst", query = "SELECT p FROM Processos p WHERE p.steufsiginst = :steufsiginst"),
    @NamedQuery(name = "Processos.findBySteufativoinst", query = "SELECT p FROM Processos p WHERE p.steufativoinst = :steufativoinst"),
    @NamedQuery(name = "Processos.findByStenucleocod", query = "SELECT p FROM Processos p WHERE p.stenucleocod = :stenucleocod"),
    @NamedQuery(name = "Processos.findByStenucleo", query = "SELECT p FROM Processos p WHERE p.stenucleo = :stenucleo"),
    @NamedQuery(name = "Processos.findByStetiponuc", query = "SELECT p FROM Processos p WHERE p.stetiponuc = :stetiponuc"),
    @NamedQuery(name = "Processos.findBySteativonuc", query = "SELECT p FROM Processos p WHERE p.steativonuc = :steativonuc"),
    @NamedQuery(name = "Processos.findBySteufativonuc", query = "SELECT p FROM Processos p WHERE p.steufativonuc = :steufativonuc"),
    @NamedQuery(name = "Processos.findByStecepnuc", query = "SELECT p FROM Processos p WHERE p.stecepnuc = :stecepnuc"),
    @NamedQuery(name = "Processos.findByStemunnuccod", query = "SELECT p FROM Processos p WHERE p.stemunnuccod = :stemunnuccod"),
    @NamedQuery(name = "Processos.findByStemunnuc", query = "SELECT p FROM Processos p WHERE p.stemunnuc = :stemunnuc"),
    @NamedQuery(name = "Processos.findByStemunativonuc", query = "SELECT p FROM Processos p WHERE p.stemunativonuc = :stemunativonuc"),
    @NamedQuery(name = "Processos.findBySteufnuccod", query = "SELECT p FROM Processos p WHERE p.steufnuccod = :steufnuccod"),
    @NamedQuery(name = "Processos.findBySteufnuc", query = "SELECT p FROM Processos p WHERE p.steufnuc = :steufnuc"),
    @NamedQuery(name = "Processos.findBySteufsignuc", query = "SELECT p FROM Processos p WHERE p.steufsignuc = :steufsignuc"),
    @NamedQuery(name = "Processos.findByStergaonuccod", query = "SELECT p FROM Processos p WHERE p.stergaonuccod = :stergaonuccod"),
    @NamedQuery(name = "Processos.findByStergaonuc", query = "SELECT p FROM Processos p WHERE p.stergaonuc = :stergaonuc"),
    @NamedQuery(name = "Processos.findByStepaisnuc", query = "SELECT p FROM Processos p WHERE p.stepaisnuc = :stepaisnuc"),
    @NamedQuery(name = "Processos.findBySteonunuccod", query = "SELECT p FROM Processos p WHERE p.steonunuccod = :steonunuccod"),
    @NamedQuery(name = "Processos.findByStepaissignuc", query = "SELECT p FROM Processos p WHERE p.stepaissignuc = :stepaissignuc"),
    @NamedQuery(name = "Processos.findBySteconvcod", query = "SELECT p FROM Processos p WHERE p.steconvcod = :steconvcod"),
    @NamedQuery(name = "Processos.findBySteconv", query = "SELECT p FROM Processos p WHERE p.steconv = :steconv"),
    @NamedQuery(name = "Processos.findBySteativoconv", query = "SELECT p FROM Processos p WHERE p.steativoconv = :steativoconv"),
    @NamedQuery(name = "Processos.findByStecoordcod", query = "SELECT p FROM Processos p WHERE p.stecoordcod = :stecoordcod"),
    @NamedQuery(name = "Processos.findByStecoord", query = "SELECT p FROM Processos p WHERE p.stecoord = :stecoord"),
    @NamedQuery(name = "Processos.findBySteprovab", query = "SELECT p FROM Processos p WHERE p.steprovab = :steprovab"),
    @NamedQuery(name = "Processos.findBySteprovabcod", query = "SELECT p FROM Processos p WHERE p.steprovabcod = :steprovabcod"),
    @NamedQuery(name = "Processos.findByStemmdcod", query = "SELECT p FROM Processos p WHERE p.stemmdcod = :stemmdcod"),
    @NamedQuery(name = "Processos.findByStemmd", query = "SELECT p FROM Processos p WHERE p.stemmd = :stemmd"),
    @NamedQuery(name = "Processos.findByStemcasacod", query = "SELECT p FROM Processos p WHERE p.stemcasacod = :stemcasacod"),
    @NamedQuery(name = "Processos.findByStemcasa", query = "SELECT p FROM Processos p WHERE p.stemcasa = :stemcasa"),
    @NamedQuery(name = "Processos.findByRegdtreceb", query = "SELECT p FROM Processos p WHERE p.regdtreceb = :regdtreceb"),
    @NamedQuery(name = "Processos.findByReghorareceb", query = "SELECT p FROM Processos p WHERE p.reghorareceb = :reghorareceb"),
    @NamedQuery(name = "Processos.findBySolacreg", query = "SELECT p FROM Processos p WHERE p.solacreg = :solacreg"),
    @NamedQuery(name = "Processos.findBySolregsitcod", query = "SELECT p FROM Processos p WHERE p.solregsitcod = :solregsitcod"),
    @NamedQuery(name = "Processos.findBySolregsit", query = "SELECT p FROM Processos p WHERE p.solregsit = :solregsit"),
    @NamedQuery(name = "Processos.findByRegaceite", query = "SELECT p FROM Processos p WHERE p.regaceite = :regaceite"),
    @NamedQuery(name = "Processos.findByRegdtdevol", query = "SELECT p FROM Processos p WHERE p.regdtdevol = :regdtdevol"),
    @NamedQuery(name = "Processos.findByRegnegativa", query = "SELECT p FROM Processos p WHERE p.regnegativa = :regnegativa"),
    @NamedQuery(name = "Processos.findByRegpessoacod", query = "SELECT p FROM Processos p WHERE p.regpessoacod = :regpessoacod"),
    @NamedQuery(name = "Processos.findByRegativopes", query = "SELECT p FROM Processos p WHERE p.regativopes = :regativopes"),
    @NamedQuery(name = "Processos.findByRegcod", query = "SELECT p FROM Processos p WHERE p.regcod = :regcod"),
    @NamedQuery(name = "Processos.findByRegativo", query = "SELECT p FROM Processos p WHERE p.regativo = :regativo"),
    @NamedQuery(name = "Processos.findByRegnome", query = "SELECT p FROM Processos p WHERE p.regnome = :regnome"),
    @NamedQuery(name = "Processos.findByRegsexo", query = "SELECT p FROM Processos p WHERE p.regsexo = :regsexo"),
    @NamedQuery(name = "Processos.findByRegsexocod", query = "SELECT p FROM Processos p WHERE p.regsexocod = :regsexocod"),
    @NamedQuery(name = "Processos.findByRegdtnasc", query = "SELECT p FROM Processos p WHERE p.regdtnasc = :regdtnasc"),
    @NamedQuery(name = "Processos.findByRegemail", query = "SELECT p FROM Processos p WHERE p.regemail = :regemail"),
    @NamedQuery(name = "Processos.findByRegcpf", query = "SELECT p FROM Processos p WHERE p.regcpf = :regcpf"),
    @NamedQuery(name = "Processos.findByRegcns", query = "SELECT p FROM Processos p WHERE p.regcns = :regcns"),
    @NamedQuery(name = "Processos.findByRegmunrescod", query = "SELECT p FROM Processos p WHERE p.regmunrescod = :regmunrescod"),
    @NamedQuery(name = "Processos.findByRegmunres", query = "SELECT p FROM Processos p WHERE p.regmunres = :regmunres"),
    @NamedQuery(name = "Processos.findByRegleingcod", query = "SELECT p FROM Processos p WHERE p.regleingcod = :regleingcod"),
    @NamedQuery(name = "Processos.findByRegleing", query = "SELECT p FROM Processos p WHERE p.regleing = :regleing"),
    @NamedQuery(name = "Processos.findByReginglescod", query = "SELECT p FROM Processos p WHERE p.reginglescod = :reginglescod"),
    @NamedQuery(name = "Processos.findByRegingles", query = "SELECT p FROM Processos p WHERE p.regingles = :regingles"),
    @NamedQuery(name = "Processos.findByRegescolcod", query = "SELECT p FROM Processos p WHERE p.regescolcod = :regescolcod"),
    @NamedQuery(name = "Processos.findByRegescol", query = "SELECT p FROM Processos p WHERE p.regescol = :regescol"),
    @NamedQuery(name = "Processos.findByRegprofisreg", query = "SELECT p FROM Processos p WHERE p.regprofisreg = :regprofisreg"),
    @NamedQuery(name = "Processos.findByRegprofiscod", query = "SELECT p FROM Processos p WHERE p.regprofiscod = :regprofiscod"),
    @NamedQuery(name = "Processos.findByRegprofis", query = "SELECT p FROM Processos p WHERE p.regprofis = :regprofis"),
    @NamedQuery(name = "Processos.findByReghabprofis", query = "SELECT p FROM Processos p WHERE p.reghabprofis = :reghabprofis"),
    @NamedQuery(name = "Processos.findByRegespeccod", query = "SELECT p FROM Processos p WHERE p.regespeccod = :regespeccod"),
    @NamedQuery(name = "Processos.findByRegativoesp", query = "SELECT p FROM Processos p WHERE p.regativoesp = :regativoesp"),
    @NamedQuery(name = "Processos.findByRegespec", query = "SELECT p FROM Processos p WHERE p.regespec = :regespec"),
    @NamedQuery(name = "Processos.findByReganoespec", query = "SELECT p FROM Processos p WHERE p.reganoespec = :reganoespec"),
    @NamedQuery(name = "Processos.findByRegtpespcod", query = "SELECT p FROM Processos p WHERE p.regtpespcod = :regtpespcod"),
    @NamedQuery(name = "Processos.findByRegtpesp", query = "SELECT p FROM Processos p WHERE p.regtpesp = :regtpesp"),
    @NamedQuery(name = "Processos.findByRegfamcbocod", query = "SELECT p FROM Processos p WHERE p.regfamcbocod = :regfamcbocod"),
    @NamedQuery(name = "Processos.findByRegfamcbo", query = "SELECT p FROM Processos p WHERE p.regfamcbo = :regfamcbo"),
    @NamedQuery(name = "Processos.findByRegcbocod", query = "SELECT p FROM Processos p WHERE p.regcbocod = :regcbocod"),
    @NamedQuery(name = "Processos.findByRegcbo", query = "SELECT p FROM Processos p WHERE p.regcbo = :regcbo"),
    @NamedQuery(name = "Processos.findByRegnucleocod", query = "SELECT p FROM Processos p WHERE p.regnucleocod = :regnucleocod"),
    @NamedQuery(name = "Processos.findByRegativonuc", query = "SELECT p FROM Processos p WHERE p.regativonuc = :regativonuc"),
    @NamedQuery(name = "Processos.findByRegnucleo", query = "SELECT p FROM Processos p WHERE p.regnucleo = :regnucleo"),
    @NamedQuery(name = "Processos.findByRegnucatucod", query = "SELECT p FROM Processos p WHERE p.regnucatucod = :regnucatucod"),
    @NamedQuery(name = "Processos.findByRegnucatu", query = "SELECT p FROM Processos p WHERE p.regnucatu = :regnucatu"),
    @NamedQuery(name = "Processos.findByRegmunnuccod", query = "SELECT p FROM Processos p WHERE p.regmunnuccod = :regmunnuccod"),
    @NamedQuery(name = "Processos.findByRegmunnuc", query = "SELECT p FROM Processos p WHERE p.regmunnuc = :regmunnuc"),
    @NamedQuery(name = "Processos.findByReguf", query = "SELECT p FROM Processos p WHERE p.reguf = :reguf"),
    @NamedQuery(name = "Processos.findByRegonunuccod", query = "SELECT p FROM Processos p WHERE p.regonunuccod = :regonunuccod"),
    @NamedQuery(name = "Processos.findByRegpaisnuc", query = "SELECT p FROM Processos p WHERE p.regpaisnuc = :regpaisnuc"),
    @NamedQuery(name = "Processos.findByRegsignuc", query = "SELECT p FROM Processos p WHERE p.regsignuc = :regsignuc"),
    @NamedQuery(name = "Processos.findByRegconvcod", query = "SELECT p FROM Processos p WHERE p.regconvcod = :regconvcod"),
    @NamedQuery(name = "Processos.findByRegconv", query = "SELECT p FROM Processos p WHERE p.regconv = :regconv"),
    @NamedQuery(name = "Processos.findByRegativoconv", query = "SELECT p FROM Processos p WHERE p.regativoconv = :regativoconv"),
    @NamedQuery(name = "Processos.findByRegchoraria", query = "SELECT p FROM Processos p WHERE p.regchoraria = :regchoraria"),
    @NamedQuery(name = "Processos.findByRegciap1cod", query = "SELECT p FROM Processos p WHERE p.regciap1cod = :regciap1cod"),
    @NamedQuery(name = "Processos.findByRegciap1", query = "SELECT p FROM Processos p WHERE p.regciap1 = :regciap1"),
    @NamedQuery(name = "Processos.findByRegciap2cod", query = "SELECT p FROM Processos p WHERE p.regciap2cod = :regciap2cod"),
    @NamedQuery(name = "Processos.findByRegciap2", query = "SELECT p FROM Processos p WHERE p.regciap2 = :regciap2"),
    @NamedQuery(name = "Processos.findByRegciap3cod", query = "SELECT p FROM Processos p WHERE p.regciap3cod = :regciap3cod"),
    @NamedQuery(name = "Processos.findByRegciap3", query = "SELECT p FROM Processos p WHERE p.regciap3 = :regciap3"),
    @NamedQuery(name = "Processos.findByRegcid1cod", query = "SELECT p FROM Processos p WHERE p.regcid1cod = :regcid1cod"),
    @NamedQuery(name = "Processos.findByRegcid1", query = "SELECT p FROM Processos p WHERE p.regcid1 = :regcid1"),
    @NamedQuery(name = "Processos.findByRegcid2cod", query = "SELECT p FROM Processos p WHERE p.regcid2cod = :regcid2cod"),
    @NamedQuery(name = "Processos.findByRegcid2", query = "SELECT p FROM Processos p WHERE p.regcid2 = :regcid2"),
    @NamedQuery(name = "Processos.findByRegagvideo", query = "SELECT p FROM Processos p WHERE p.regagvideo = :regagvideo"),
    @NamedQuery(name = "Processos.findByRegdtagendou", query = "SELECT p FROM Processos p WHERE p.regdtagendou = :regdtagendou"),
    @NamedQuery(name = "Processos.findByRegdtconfagend", query = "SELECT p FROM Processos p WHERE p.regdtconfagend = :regdtconfagend"),
    @NamedQuery(name = "Processos.findByRegdtcanage", query = "SELECT p FROM Processos p WHERE p.regdtcanage = :regdtcanage"),
    @NamedQuery(name = "Processos.findByRegdtreag", query = "SELECT p FROM Processos p WHERE p.regdtreag = :regdtreag"),
    @NamedQuery(name = "Processos.findByMonnome", query = "SELECT p FROM Processos p WHERE p.monnome = :monnome"),
    @NamedQuery(name = "Processos.findByRegdtenvio", query = "SELECT p FROM Processos p WHERE p.regdtenvio = :regdtenvio"),
    @NamedQuery(name = "Processos.findByReghoraenvio", query = "SELECT p FROM Processos p WHERE p.reghoraenvio = :reghoraenvio"),
    @NamedQuery(name = "Processos.findByRegenvio", query = "SELECT p FROM Processos p WHERE p.regenvio = :regenvio"),
    @NamedQuery(name = "Processos.findByConsdtacresp", query = "SELECT p FROM Processos p WHERE p.consdtacresp = :consdtacresp"),
    @NamedQuery(name = "Processos.findByConshracresp", query = "SELECT p FROM Processos p WHERE p.conshracresp = :conshracresp"),
    @NamedQuery(name = "Processos.findByConsdtdevol", query = "SELECT p FROM Processos p WHERE p.consdtdevol = :consdtdevol"),
    @NamedQuery(name = "Processos.findByConspessoacos", query = "SELECT p FROM Processos p WHERE p.conspessoacos = :conspessoacos"),
    @NamedQuery(name = "Processos.findByConsativopes", query = "SELECT p FROM Processos p WHERE p.consativopes = :consativopes"),
    @NamedQuery(name = "Processos.findByConscod", query = "SELECT p FROM Processos p WHERE p.conscod = :conscod"),
    @NamedQuery(name = "Processos.findByConsativo", query = "SELECT p FROM Processos p WHERE p.consativo = :consativo"),
    @NamedQuery(name = "Processos.findByConsaceite", query = "SELECT p FROM Processos p WHERE p.consaceite = :consaceite"),
    @NamedQuery(name = "Processos.findByConsnome", query = "SELECT p FROM Processos p WHERE p.consnome = :consnome"),
    @NamedQuery(name = "Processos.findByConssexo", query = "SELECT p FROM Processos p WHERE p.conssexo = :conssexo"),
    @NamedQuery(name = "Processos.findByConssexocod", query = "SELECT p FROM Processos p WHERE p.conssexocod = :conssexocod"),
    @NamedQuery(name = "Processos.findByConsdtnasc", query = "SELECT p FROM Processos p WHERE p.consdtnasc = :consdtnasc"),
    @NamedQuery(name = "Processos.findByConsemail", query = "SELECT p FROM Processos p WHERE p.consemail = :consemail"),
    @NamedQuery(name = "Processos.findByConscpf", query = "SELECT p FROM Processos p WHERE p.conscpf = :conscpf"),
    @NamedQuery(name = "Processos.findByConscns", query = "SELECT p FROM Processos p WHERE p.conscns = :conscns"),
    @NamedQuery(name = "Processos.findByConsmunrescod", query = "SELECT p FROM Processos p WHERE p.consmunrescod = :consmunrescod"),
    @NamedQuery(name = "Processos.findByConsmunres", query = "SELECT p FROM Processos p WHERE p.consmunres = :consmunres"),
    @NamedQuery(name = "Processos.findByConsmuniccod", query = "SELECT p FROM Processos p WHERE p.consmuniccod = :consmuniccod"),
    @NamedQuery(name = "Processos.findByConsmunic", query = "SELECT p FROM Processos p WHERE p.consmunic = :consmunic"),
    @NamedQuery(name = "Processos.findByConsuf", query = "SELECT p FROM Processos p WHERE p.consuf = :consuf"),
    @NamedQuery(name = "Processos.findByConsleingcod", query = "SELECT p FROM Processos p WHERE p.consleingcod = :consleingcod"),
    @NamedQuery(name = "Processos.findByConsleing", query = "SELECT p FROM Processos p WHERE p.consleing = :consleing"),
    @NamedQuery(name = "Processos.findByConsinglescod", query = "SELECT p FROM Processos p WHERE p.consinglescod = :consinglescod"),
    @NamedQuery(name = "Processos.findByConsingles", query = "SELECT p FROM Processos p WHERE p.consingles = :consingles"),
    @NamedQuery(name = "Processos.findByConsescolcod", query = "SELECT p FROM Processos p WHERE p.consescolcod = :consescolcod"),
    @NamedQuery(name = "Processos.findByConsescol", query = "SELECT p FROM Processos p WHERE p.consescol = :consescol"),
    @NamedQuery(name = "Processos.findByConsprofisreg", query = "SELECT p FROM Processos p WHERE p.consprofisreg = :consprofisreg"),
    @NamedQuery(name = "Processos.findByConsprofiscod", query = "SELECT p FROM Processos p WHERE p.consprofiscod = :consprofiscod"),
    @NamedQuery(name = "Processos.findByConsprofis", query = "SELECT p FROM Processos p WHERE p.consprofis = :consprofis"),
    @NamedQuery(name = "Processos.findByConshabprofis", query = "SELECT p FROM Processos p WHERE p.conshabprofis = :conshabprofis"),
    @NamedQuery(name = "Processos.findByConsespeccod", query = "SELECT p FROM Processos p WHERE p.consespeccod = :consespeccod"),
    @NamedQuery(name = "Processos.findByConsespec", query = "SELECT p FROM Processos p WHERE p.consespec = :consespec"),
    @NamedQuery(name = "Processos.findByConstpespcod", query = "SELECT p FROM Processos p WHERE p.constpespcod = :constpespcod"),
    @NamedQuery(name = "Processos.findByConstpesp", query = "SELECT p FROM Processos p WHERE p.constpesp = :constpesp"),
    @NamedQuery(name = "Processos.findByConsanoespec", query = "SELECT p FROM Processos p WHERE p.consanoespec = :consanoespec"),
    @NamedQuery(name = "Processos.findByConsativoesp", query = "SELECT p FROM Processos p WHERE p.consativoesp = :consativoesp"),
    @NamedQuery(name = "Processos.findByConsfamcbocod", query = "SELECT p FROM Processos p WHERE p.consfamcbocod = :consfamcbocod"),
    @NamedQuery(name = "Processos.findByConsfamcbo", query = "SELECT p FROM Processos p WHERE p.consfamcbo = :consfamcbo"),
    @NamedQuery(name = "Processos.findByConscbocod", query = "SELECT p FROM Processos p WHERE p.conscbocod = :conscbocod"),
    @NamedQuery(name = "Processos.findByConscbo", query = "SELECT p FROM Processos p WHERE p.conscbo = :conscbo"),
    @NamedQuery(name = "Processos.findByConsnucleocod", query = "SELECT p FROM Processos p WHERE p.consnucleocod = :consnucleocod"),
    @NamedQuery(name = "Processos.findByConsnucleo", query = "SELECT p FROM Processos p WHERE p.consnucleo = :consnucleo"),
    @NamedQuery(name = "Processos.findByConsativonuc", query = "SELECT p FROM Processos p WHERE p.consativonuc = :consativonuc"),
    @NamedQuery(name = "Processos.findByConsnucatucod", query = "SELECT p FROM Processos p WHERE p.consnucatucod = :consnucatucod"),
    @NamedQuery(name = "Processos.findByConsnucatu", query = "SELECT p FROM Processos p WHERE p.consnucatu = :consnucatu"),
    @NamedQuery(name = "Processos.findByConsmunnuccod", query = "SELECT p FROM Processos p WHERE p.consmunnuccod = :consmunnuccod"),
    @NamedQuery(name = "Processos.findByConsmunnuc", query = "SELECT p FROM Processos p WHERE p.consmunnuc = :consmunnuc"),
    @NamedQuery(name = "Processos.findByConsonunuccod", query = "SELECT p FROM Processos p WHERE p.consonunuccod = :consonunuccod"),
    @NamedQuery(name = "Processos.findByConspaisnuc", query = "SELECT p FROM Processos p WHERE p.conspaisnuc = :conspaisnuc"),
    @NamedQuery(name = "Processos.findByConssignuc", query = "SELECT p FROM Processos p WHERE p.conssignuc = :conssignuc"),
    @NamedQuery(name = "Processos.findByConsconvcod", query = "SELECT p FROM Processos p WHERE p.consconvcod = :consconvcod"),
    @NamedQuery(name = "Processos.findByConsconv", query = "SELECT p FROM Processos p WHERE p.consconv = :consconv"),
    @NamedQuery(name = "Processos.findByConsativoconv", query = "SELECT p FROM Processos p WHERE p.consativoconv = :consativoconv"),
    @NamedQuery(name = "Processos.findByConshorasres", query = "SELECT p FROM Processos p WHERE p.conshorasres = :conshorasres"),
    @NamedQuery(name = "Processos.findByConschoraria", query = "SELECT p FROM Processos p WHERE p.conschoraria = :conschoraria"),
    @NamedQuery(name = "Processos.findBySolrepet", query = "SELECT p FROM Processos p WHERE p.solrepet = :solrepet"),
    @NamedQuery(name = "Processos.findBySolresposta", query = "SELECT p FROM Processos p WHERE p.solresposta = :solresposta"),
    @NamedQuery(name = "Processos.findBySolcompresp", query = "SELECT p FROM Processos p WHERE p.solcompresp = :solcompresp"),
    @NamedQuery(name = "Processos.findBySolcompcod", query = "SELECT p FROM Processos p WHERE p.solcompcod = :solcompcod"),
    @NamedQuery(name = "Processos.findBySolatributos", query = "SELECT p FROM Processos p WHERE p.solatributos = :solatributos"),
    @NamedQuery(name = "Processos.findBySoleducper", query = "SELECT p FROM Processos p WHERE p.soleducper = :soleducper"),
    @NamedQuery(name = "Processos.findBySolrefs", query = "SELECT p FROM Processos p WHERE p.solrefs = :solrefs"),
    @NamedQuery(name = "Processos.findBySolsumevid", query = "SELECT p FROM Processos p WHERE p.solsumevid = :solsumevid"),
    @NamedQuery(name = "Processos.findBySolestbusca", query = "SELECT p FROM Processos p WHERE p.solestbusca = :solestbusca"),
    @NamedQuery(name = "Processos.findBySolmantenccod", query = "SELECT p FROM Processos p WHERE p.solmantenccod = :solmantenccod"),
    @NamedQuery(name = "Processos.findBySolmantenc", query = "SELECT p FROM Processos p WHERE p.solmantenc = :solmantenc"),
    @NamedQuery(name = "Processos.findBySolsugenccod", query = "SELECT p FROM Processos p WHERE p.solsugenccod = :solsugenccod"),
    @NamedQuery(name = "Processos.findBySolsugenc", query = "SELECT p FROM Processos p WHERE p.solsugenc = :solsugenc"),
    @NamedQuery(name = "Processos.findBySolcboprocod", query = "SELECT p FROM Processos p WHERE p.solcboprocod = :solcboprocod"),
    @NamedQuery(name = "Processos.findBySolcbopro", query = "SELECT p FROM Processos p WHERE p.solcbopro = :solcbopro"),
    @NamedQuery(name = "Processos.findBySolcboespcod", query = "SELECT p FROM Processos p WHERE p.solcboespcod = :solcboespcod"),
    @NamedQuery(name = "Processos.findBySolcboesp", query = "SELECT p FROM Processos p WHERE p.solcboesp = :solcboesp"),
    @NamedQuery(name = "Processos.findBySoldtnaspac", query = "SELECT p FROM Processos p WHERE p.soldtnaspac = :soldtnaspac"),
    @NamedQuery(name = "Processos.findBySolmaenome", query = "SELECT p FROM Processos p WHERE p.solmaenome = :solmaenome"),
    @NamedQuery(name = "Processos.findBySoljanecami", query = "SELECT p FROM Processos p WHERE p.soljanecami = :soljanecami"),
    @NamedQuery(name = "Processos.findBySolcnspac", query = "SELECT p FROM Processos p WHERE p.solcnspac = :solcnspac"),
    @NamedQuery(name = "Processos.findBySolpacnome", query = "SELECT p FROM Processos p WHERE p.solpacnome = :solpacnome"),
    @NamedQuery(name = "Processos.findBySolpacsexo", query = "SELECT p FROM Processos p WHERE p.solpacsexo = :solpacsexo"),
    @NamedQuery(name = "Processos.findBySolpacsexocod", query = "SELECT p FROM Processos p WHERE p.solpacsexocod = :solpacsexocod"),
    @NamedQuery(name = "Processos.findBySolpaccpf", query = "SELECT p FROM Processos p WHERE p.solpaccpf = :solpaccpf"),
    @NamedQuery(name = "Processos.findBySolsofcod", query = "SELECT p FROM Processos p WHERE p.solsofcod = :solsofcod"),
    @NamedQuery(name = "Processos.findBySolsof", query = "SELECT p FROM Processos p WHERE p.solsof = :solsof"),
    @NamedQuery(name = "Processos.findBySoldtenvresp", query = "SELECT p FROM Processos p WHERE p.soldtenvresp = :soldtenvresp"),
    @NamedQuery(name = "Processos.findBySolresp", query = "SELECT p FROM Processos p WHERE p.solresp = :solresp"),
    @NamedQuery(name = "Processos.findBySolhrenvresp", query = "SELECT p FROM Processos p WHERE p.solhrenvresp = :solhrenvresp"),
    @NamedQuery(name = "Processos.findBySoljustdevresp", query = "SELECT p FROM Processos p WHERE p.soljustdevresp = :soljustdevresp"),
    @NamedQuery(name = "Processos.findBySolrespaceita", query = "SELECT p FROM Processos p WHERE p.solrespaceita = :solrespaceita"),
    @NamedQuery(name = "Processos.findBySolavalativa", query = "SELECT p FROM Processos p WHERE p.solavalativa = :solavalativa"),
    @NamedQuery(name = "Processos.findBySoldtavalin", query = "SELECT p FROM Processos p WHERE p.soldtavalin = :soldtavalin"),
    @NamedQuery(name = "Processos.findBySolhravalin", query = "SELECT p FROM Processos p WHERE p.solhravalin = :solhravalin"),
    @NamedQuery(name = "Processos.findBySolsatisfcod", query = "SELECT p FROM Processos p WHERE p.solsatisfcod = :solsatisfcod"),
    @NamedQuery(name = "Processos.findBySolsatisf", query = "SELECT p FROM Processos p WHERE p.solsatisf = :solsatisf"),
    @NamedQuery(name = "Processos.findBySolclassifcod", query = "SELECT p FROM Processos p WHERE p.solclassifcod = :solclassifcod"),
    @NamedQuery(name = "Processos.findBySolclassif", query = "SELECT p FROM Processos p WHERE p.solclassif = :solclassif"),
    @NamedQuery(name = "Processos.findBySolevitrefcod", query = "SELECT p FROM Processos p WHERE p.solevitrefcod = :solevitrefcod"),
    @NamedQuery(name = "Processos.findBySolevitref", query = "SELECT p FROM Processos p WHERE p.solevitref = :solevitref"),
    @NamedQuery(name = "Processos.findBySolindrefcod", query = "SELECT p FROM Processos p WHERE p.solindrefcod = :solindrefcod"),
    @NamedQuery(name = "Processos.findBySolindref", query = "SELECT p FROM Processos p WHERE p.solindref = :solindref"),
    @NamedQuery(name = "Processos.findBySolcritsug", query = "SELECT p FROM Processos p WHERE p.solcritsug = :solcritsug"),
    @NamedQuery(name = "Processos.findByRespativa", query = "SELECT p FROM Processos p WHERE p.respativa = :respativa"),
    @NamedQuery(name = "Processos.findBySolatraso", query = "SELECT p FROM Processos p WHERE p.solatraso = :solatraso"),
    @NamedQuery(name = "Processos.findBySolatrasada", query = "SELECT p FROM Processos p WHERE p.solatrasada = :solatrasada"),
    @NamedQuery(name = "Processos.findBySolsitcod", query = "SELECT p FROM Processos p WHERE p.solsitcod = :solsitcod"),
    @NamedQuery(name = "Processos.findBySolsit", query = "SELECT p FROM Processos p WHERE p.solsit = :solsit"),
    @NamedQuery(name = "Processos.findBySoldtsteleit", query = "SELECT p FROM Processos p WHERE p.soldtsteleit = :soldtsteleit"),
    @NamedQuery(name = "Processos.findBySoldtavalfim", query = "SELECT p FROM Processos p WHERE p.soldtavalfim = :soldtavalfim"),
    @NamedQuery(name = "Processos.findBySolavalfim", query = "SELECT p FROM Processos p WHERE p.solavalfim = :solavalfim"),
    @NamedQuery(name = "Processos.findBySolhravalfim", query = "SELECT p FROM Processos p WHERE p.solhravalfim = :solhravalfim"),
    @NamedQuery(name = "Processos.findBySolprecomp", query = "SELECT p FROM Processos p WHERE p.solprecomp = :solprecomp"),
    @NamedQuery(name = "Processos.findBySoltpo1", query = "SELECT p FROM Processos p WHERE p.soltpo1 = :soltpo1"),
    @NamedQuery(name = "Processos.findBySoltpo2", query = "SELECT p FROM Processos p WHERE p.soltpo2 = :soltpo2"),
    @NamedQuery(name = "Processos.findBySoltpo3", query = "SELECT p FROM Processos p WHERE p.soltpo3 = :soltpo3"),
    @NamedQuery(name = "Processos.findBySoltpo4", query = "SELECT p FROM Processos p WHERE p.soltpo4 = :soltpo4"),
    @NamedQuery(name = "Processos.findBySoltpo5", query = "SELECT p FROM Processos p WHERE p.soltpo5 = :soltpo5"),
    @NamedQuery(name = "Processos.findBySoltpo6", query = "SELECT p FROM Processos p WHERE p.soltpo6 = :soltpo6"),
    @NamedQuery(name = "Processos.findBySoltresp", query = "SELECT p FROM Processos p WHERE p.soltresp = :soltresp"),
    @NamedQuery(name = "Processos.findBySoltaval", query = "SELECT p FROM Processos p WHERE p.soltaval = :soltaval"),
    @NamedQuery(name = "Processos.findBySoltreg", query = "SELECT p FROM Processos p WHERE p.soltreg = :soltreg")})
public class Processos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "solcod")
    private Integer solcod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solservcod")
    private int solservcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "solserv")
    private String solserv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soltipocod")
    private int soltipocod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "soltipo")
    private String soltipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "soldtabertura")
    private String soldtabertura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "solhorabert")
    private String solhorabert;
    @Size(max = 10)
    @Column(name = "soldtenvio")
    private String soldtenvio;
    @Size(max = 7)
    @Column(name = "solenvio")
    private String solenvio;
    @Size(max = 8)
    @Column(name = "solhoranevio")
    private String solhoranevio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solativo")
    private int solativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solpacespcod")
    private int solpacespcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "solpacespec")
    private String solpacespec;
    @Column(name = "solintencacod")
    private Integer solintencacod;
    @Size(max = 3)
    @Column(name = "solintencami")
    private String solintencami;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1212)
    @Column(name = "soldescricao")
    private String soldescricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stepessoacod")
    private int stepessoacod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativopes")
    private int steativopes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stecod")
    private int stecod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativo")
    private int steativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 47)
    @Column(name = "stenome")
    private String stenome;
    @Column(name = "stesexocod")
    private Integer stesexocod;
    @Size(max = 1)
    @Column(name = "stesexo")
    private String stesexo;
    @Size(max = 10)
    @Column(name = "stedtnasc")
    private String stedtnasc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "steemail")
    private String steemail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "stecpf")
    private String stecpf;
    @Column(name = "stecns")
    private Integer stecns;
    @Column(name = "steoutdoc")
    private Integer steoutdoc;
    @Column(name = "stecepres")
    private Integer stecepres;
    @Column(name = "stemunrescod")
    private Integer stemunrescod;
    @Size(max = 20)
    @Column(name = "stemunres")
    private String stemunres;
    @Column(name = "stemunatres")
    private Integer stemunatres;
    @Column(name = "steufrescod")
    private Integer steufrescod;
    @Size(max = 8)
    @Column(name = "steufres")
    private String steufres;
    @Column(name = "steufativores")
    private Integer steufativores;
    @Size(max = 2)
    @Column(name = "steufsigres")
    private String steufsigres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steleingcod")
    private int steleingcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "steleing")
    private String steleing;
    @Column(name = "steinglescod")
    private Integer steinglescod;
    @Size(max = 20)
    @Column(name = "steingles")
    private String steingles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steescolcod")
    private int steescolcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "steescol")
    private String steescol;
    @Column(name = "steprofisreg")
    private Integer steprofisreg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steprofiscod")
    private int steprofiscod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "steprofis")
    private String steprofis;
    @Column(name = "stehabprofis")
    private Integer stehabprofis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steespeccod")
    private int steespeccod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 51)
    @Column(name = "steespec")
    private String steespec;
    @Column(name = "steanoespec")
    private Integer steanoespec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativoesp")
    private int steativoesp;
    @Column(name = "stetpespcod")
    private Integer stetpespcod;
    @Size(max = 5)
    @Column(name = "stetpesp")
    private String stetpesp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stefamcbocod")
    private int stefamcbocod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 53)
    @Column(name = "stefamcbo")
    private String stefamcbo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stecbocod")
    private int stecbocod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 57)
    @Column(name = "stecbo")
    private String stecbo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steeqcod")
    private int steeqcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "steequipe")
    private String steequipe;
    @Column(name = "steine")
    private Integer steine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativoeq")
    private int steativoeq;
    @Column(name = "stetipoeqcod")
    private Integer stetipoeqcod;
    @Size(max = 58)
    @Column(name = "stetipoeq")
    private String stetipoeq;
    @Column(name = "stetpativoeq")
    private Integer stetpativoeq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steunidcod")
    private int steunidcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "steunid")
    private String steunid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stecnesunid")
    private int stecnesunid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativounid")
    private int steativounid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stetipounidcod")
    private int stetipounidcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "stetipounid")
    private String stetipounid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "stesetorunid")
    private String stesetorunid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stetpativounid")
    private int stetpativounid;
    @Column(name = "stecep")
    private Integer stecep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stemuniccod")
    private int stemuniccod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "stemunic")
    private String stemunic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "steuf")
    private String steuf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativouf")
    private int steativouf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "stesiguf")
    private String stesiguf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steufcod")
    private int steufcod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativomun")
    private int steativomun;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steregcod")
    private int steregcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "stereg")
    private String stereg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steonu")
    private int steonu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "stepais")
    private String stepais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "stesigpais")
    private String stesigpais;
    @Size(max = 1)
    @Column(name = "stenequs")
    private String stenequs;
    @Size(max = 1)
    @Column(name = "steeqcomus")
    private String steeqcomus;
    @Size(max = 1)
    @Column(name = "steeqintus")
    private String steeqintus;
    @Size(max = 1)
    @Column(name = "stenwebcus")
    private String stenwebcus;
    @Size(max = 1)
    @Column(name = "stenmicus")
    private String stenmicus;
    @Size(max = 1)
    @Column(name = "stensomus")
    private String stensomus;
    @Size(max = 1)
    @Column(name = "stenfoneus")
    private String stenfoneus;
    @Size(max = 1)
    @Column(name = "steintadequs")
    private String steintadequs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "steinst")
    private String steinst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steinstcod")
    private int steinstcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "stetipoinst")
    private String stetipoinst;
    @Size(max = 1)
    @Column(name = "stecepinst")
    private String stecepinst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stemuninstcod")
    private int stemuninstcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "stemuninst")
    private String stemuninst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stemunsativoinst")
    private int stemunsativoinst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steonuinst")
    private int steonuinst;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "stepaisinst")
    private String stepaisinst;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "stepaissiginst")
    private String stepaissiginst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stereginstcod")
    private int stereginstcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "stereginst")
    private String stereginst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steufinstcod")
    private int steufinstcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "steufinst")
    private String steufinst;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "steufsiginst")
    private String steufsiginst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steufativoinst")
    private int steufativoinst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stenucleocod")
    private int stenucleocod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "stenucleo")
    private String stenucleo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "stetiponuc")
    private String stetiponuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativonuc")
    private int steativonuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steufativonuc")
    private int steufativonuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stecepnuc")
    private int stecepnuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stemunnuccod")
    private int stemunnuccod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "stemunnuc")
    private String stemunnuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stemunativonuc")
    private int stemunativonuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steufnuccod")
    private int steufnuccod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "steufnuc")
    private String steufnuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "steufsignuc")
    private String steufsignuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stergaonuccod")
    private int stergaonuccod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "stergaonuc")
    private String stergaonuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "stepaisnuc")
    private String stepaisnuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steonunuccod")
    private int steonunuccod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "stepaissignuc")
    private String stepaissignuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steconvcod")
    private int steconvcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "steconv")
    private String steconv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steativoconv")
    private int steativoconv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stecoordcod")
    private int stecoordcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "stecoord")
    private String stecoord;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "steprovab")
    private String steprovab;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steprovabcod")
    private int steprovabcod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stemmdcod")
    private int stemmdcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "stemmd")
    private String stemmd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stemcasacod")
    private int stemcasacod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "stemcasa")
    private String stemcasa;
    @Size(max = 10)
    @Column(name = "regdtreceb")
    private String regdtreceb;
    @Size(max = 8)
    @Column(name = "reghorareceb")
    private String reghorareceb;
    @Size(max = 7)
    @Column(name = "solacreg")
    private String solacreg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 42)
    @Column(name = "solregsitcod")
    private String solregsitcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 42)
    @Column(name = "solregsit")
    private String solregsit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regaceite")
    private int regaceite;
    @Size(max = 10)
    @Column(name = "regdtdevol")
    private String regdtdevol;
    @Size(max = 720)
    @Column(name = "regnegativa")
    private String regnegativa;
    @Column(name = "regpessoacod")
    private Integer regpessoacod;
    @Column(name = "regativopes")
    private Integer regativopes;
    @Column(name = "regcod")
    private Integer regcod;
    @Column(name = "regativo")
    private Integer regativo;
    @Size(max = 36)
    @Column(name = "regnome")
    private String regnome;
    @Size(max = 8)
    @Column(name = "regsexo")
    private String regsexo;
    @Column(name = "regsexocod")
    private Integer regsexocod;
    @Size(max = 1)
    @Column(name = "regdtnasc")
    private String regdtnasc;
    @Size(max = 23)
    @Column(name = "regemail")
    private String regemail;
    @Size(max = 11)
    @Column(name = "regcpf")
    private String regcpf;
    @Size(max = 1)
    @Column(name = "regcns")
    private String regcns;
    @Size(max = 1)
    @Column(name = "regmunrescod")
    private String regmunrescod;
    @Size(max = 1)
    @Column(name = "regmunres")
    private String regmunres;
    @Column(name = "regleingcod")
    private Integer regleingcod;
    @Size(max = 3)
    @Column(name = "regleing")
    private String regleing;
    @Column(name = "reginglescod")
    private Integer reginglescod;
    @Size(max = 11)
    @Column(name = "regingles")
    private String regingles;
    @Column(name = "regescolcod")
    private Integer regescolcod;
    @Size(max = 8)
    @Column(name = "regescol")
    private String regescol;
    @Size(max = 1)
    @Column(name = "regprofisreg")
    private String regprofisreg;
    @Column(name = "regprofiscod")
    private Integer regprofiscod;
    @Size(max = 20)
    @Column(name = "regprofis")
    private String regprofis;
    @Size(max = 1)
    @Column(name = "reghabprofis")
    private String reghabprofis;
    @Column(name = "regespeccod")
    private Integer regespeccod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regativoesp")
    private int regativoesp;
    @Size(max = 7)
    @Column(name = "regespec")
    private String regespec;
    @Size(max = 1)
    @Column(name = "reganoespec")
    private String reganoespec;
    @Column(name = "regtpespcod")
    private Integer regtpespcod;
    @Size(max = 5)
    @Column(name = "regtpesp")
    private String regtpesp;
    @Column(name = "regfamcbocod")
    private Integer regfamcbocod;
    @Size(max = 16)
    @Column(name = "regfamcbo")
    private String regfamcbo;
    @Column(name = "regcbocod")
    private Integer regcbocod;
    @Size(max = 14)
    @Column(name = "regcbo")
    private String regcbo;
    @Column(name = "regnucleocod")
    private Integer regnucleocod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regativonuc")
    private int regativonuc;
    @Size(max = 15)
    @Column(name = "regnucleo")
    private String regnucleo;
    @Column(name = "regnucatucod")
    private Integer regnucatucod;
    @Size(max = 15)
    @Column(name = "regnucatu")
    private String regnucatu;
    @Column(name = "regmunnuccod")
    private Integer regmunnuccod;
    @Size(max = 8)
    @Column(name = "regmunnuc")
    private String regmunnuc;
    @Size(max = 8)
    @Column(name = "reguf")
    private String reguf;
    @Column(name = "regonunuccod")
    private Integer regonunuccod;
    @Size(max = 6)
    @Column(name = "regpaisnuc")
    private String regpaisnuc;
    @Size(max = 3)
    @Column(name = "regsignuc")
    private String regsignuc;
    @Column(name = "regconvcod")
    private Integer regconvcod;
    @Size(max = 15)
    @Column(name = "regconv")
    private String regconv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regativoconv")
    private int regativoconv;
    @Size(max = 1)
    @Column(name = "regchoraria")
    private String regchoraria;
    @Size(max = 3)
    @Column(name = "regciap1cod")
    private String regciap1cod;
    @Size(max = 63)
    @Column(name = "regciap1")
    private String regciap1;
    @Size(max = 3)
    @Column(name = "regciap2cod")
    private String regciap2cod;
    @Size(max = 72)
    @Column(name = "regciap2")
    private String regciap2;
    @Size(max = 1)
    @Column(name = "regciap3cod")
    private String regciap3cod;
    @Size(max = 1)
    @Column(name = "regciap3")
    private String regciap3;
    @Size(max = 4)
    @Column(name = "regcid1cod")
    private String regcid1cod;
    @Size(max = 95)
    @Column(name = "regcid1")
    private String regcid1;
    @Size(max = 1)
    @Column(name = "regcid2cod")
    private String regcid2cod;
    @Size(max = 1)
    @Column(name = "regcid2")
    private String regcid2;
    @Size(max = 1)
    @Column(name = "regagvideo")
    private String regagvideo;
    @Size(max = 1)
    @Column(name = "regdtagendou")
    private String regdtagendou;
    @Size(max = 1)
    @Column(name = "regdtconfagend")
    private String regdtconfagend;
    @Size(max = 1)
    @Column(name = "regdtcanage")
    private String regdtcanage;
    @Size(max = 1)
    @Column(name = "regdtreag")
    private String regdtreag;
    @Size(max = 1)
    @Column(name = "monnome")
    private String monnome;
    @Size(max = 10)
    @Column(name = "regdtenvio")
    private String regdtenvio;
    @Size(max = 8)
    @Column(name = "reghoraenvio")
    private String reghoraenvio;
    @Size(max = 7)
    @Column(name = "regenvio")
    private String regenvio;
    @Size(max = 10)
    @Column(name = "consdtacresp")
    private String consdtacresp;
    @Size(max = 8)
    @Column(name = "conshracresp")
    private String conshracresp;
    @Size(max = 10)
    @Column(name = "consdtdevol")
    private String consdtdevol;
    @Column(name = "conspessoacos")
    private Integer conspessoacos;
    @Column(name = "consativopes")
    private Integer consativopes;
    @Column(name = "conscod")
    private Integer conscod;
    @Column(name = "consativo")
    private Integer consativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consaceite")
    private int consaceite;
    @Size(max = 38)
    @Column(name = "consnome")
    private String consnome;
    @Size(max = 1)
    @Column(name = "conssexo")
    private String conssexo;
    @Column(name = "conssexocod")
    private Integer conssexocod;
    @Size(max = 10)
    @Column(name = "consdtnasc")
    private String consdtnasc;
    @Size(max = 31)
    @Column(name = "consemail")
    private String consemail;
    @Size(max = 11)
    @Column(name = "conscpf")
    private String conscpf;
    @Column(name = "conscns")
    private Integer conscns;
    @Column(name = "consmunrescod")
    private Integer consmunrescod;
    @Size(max = 15)
    @Column(name = "consmunres")
    private String consmunres;
    @Column(name = "consmuniccod")
    private Integer consmuniccod;
    @Size(max = 15)
    @Column(name = "consmunic")
    private String consmunic;
    @Size(max = 8)
    @Column(name = "consuf")
    private String consuf;
    @Column(name = "consleingcod")
    private Integer consleingcod;
    @Size(max = 3)
    @Column(name = "consleing")
    private String consleing;
    @Column(name = "consinglescod")
    private Integer consinglescod;
    @Size(max = 11)
    @Column(name = "consingles")
    private String consingles;
    @Column(name = "consescolcod")
    private Integer consescolcod;
    @Size(max = 8)
    @Column(name = "consescol")
    private String consescol;
    @Column(name = "consprofisreg")
    private Integer consprofisreg;
    @Column(name = "consprofiscod")
    private Integer consprofiscod;
    @Size(max = 20)
    @Column(name = "consprofis")
    private String consprofis;
    @Column(name = "conshabprofis")
    private Integer conshabprofis;
    @Column(name = "consespeccod")
    private Integer consespeccod;
    @Size(max = 40)
    @Column(name = "consespec")
    private String consespec;
    @Column(name = "constpespcod")
    private Integer constpespcod;
    @Size(max = 5)
    @Column(name = "constpesp")
    private String constpesp;
    @Column(name = "consanoespec")
    private Integer consanoespec;
    @Column(name = "consativoesp")
    private Integer consativoesp;
    @Column(name = "consfamcbocod")
    private Integer consfamcbocod;
    @Size(max = 26)
    @Column(name = "consfamcbo")
    private String consfamcbo;
    @Column(name = "conscbocod")
    private Integer conscbocod;
    @Size(max = 44)
    @Column(name = "conscbo")
    private String conscbo;
    @Column(name = "consnucleocod")
    private Integer consnucleocod;
    @Size(max = 15)
    @Column(name = "consnucleo")
    private String consnucleo;
    @Column(name = "consativonuc")
    private Boolean consativonuc;
    @Column(name = "consnucatucod")
    private Integer consnucatucod;
    @Size(max = 15)
    @Column(name = "consnucatu")
    private String consnucatu;
    @Column(name = "consmunnuccod")
    private Integer consmunnuccod;
    @Size(max = 8)
    @Column(name = "consmunnuc")
    private String consmunnuc;
    @Column(name = "consonunuccod")
    private Integer consonunuccod;
    @Size(max = 6)
    @Column(name = "conspaisnuc")
    private String conspaisnuc;
    @Size(max = 3)
    @Column(name = "conssignuc")
    private String conssignuc;
    @Column(name = "consconvcod")
    private Integer consconvcod;
    @Size(max = 15)
    @Column(name = "consconv")
    private String consconv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consativoconv")
    private int consativoconv;
    @Size(max = 1)
    @Column(name = "conshorasres")
    private String conshorasres;
    @Size(max = 1)
    @Column(name = "conschoraria")
    private String conschoraria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solrepet")
    private int solrepet;
    @Size(max = 15474)
    @Column(name = "solresposta")
    private String solresposta;
    @Size(max = 7671)
    @Column(name = "solcompresp")
    private String solcompresp;
    @Size(max = 1)
    @Column(name = "solcompcod")
    private String solcompcod;
    @Size(max = 1738)
    @Column(name = "solatributos")
    private String solatributos;
    @Size(max = 2368)
    @Column(name = "soleducper")
    private String soleducper;
    @Size(max = 1811)
    @Column(name = "solrefs")
    private String solrefs;
    @Size(max = 637)
    @Column(name = "solsumevid")
    private String solsumevid;
    @Size(max = 113)
    @Column(name = "solestbusca")
    private String solestbusca;
    @Column(name = "solmantenccod")
    private Integer solmantenccod;
    @Size(max = 3)
    @Column(name = "solmantenc")
    private String solmantenc;
    @Column(name = "solsugenccod")
    private Integer solsugenccod;
    @Size(max = 3)
    @Column(name = "solsugenc")
    private String solsugenc;
    @Column(name = "solcboprocod")
    private Integer solcboprocod;
    @Size(max = 52)
    @Column(name = "solcbopro")
    private String solcbopro;
    @Column(name = "solcboespcod")
    private Integer solcboespcod;
    @Size(max = 24)
    @Column(name = "solcboesp")
    private String solcboesp;
    @Size(max = 10)
    @Column(name = "soldtnaspac")
    private String soldtnaspac;
    @Size(max = 28)
    @Column(name = "solmaenome")
    private String solmaenome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soljanecami")
    private int soljanecami;
    @Column(name = "solcnspac")
    private Integer solcnspac;
    @Size(max = 27)
    @Column(name = "solpacnome")
    private String solpacnome;
    @Size(max = 15)
    @Column(name = "solpacsexo")
    private String solpacsexo;
    @Column(name = "solpacsexocod")
    private Integer solpacsexocod;
    @Size(max = 12)
    @Column(name = "solpaccpf")
    private String solpaccpf;
    @Column(name = "solsofcod")
    private Integer solsofcod;
    @Size(max = 3)
    @Column(name = "solsof")
    private String solsof;
    @Size(max = 10)
    @Column(name = "soldtenvresp")
    private String soldtenvresp;
    @Size(max = 7)
    @Column(name = "solresp")
    private String solresp;
    @Size(max = 8)
    @Column(name = "solhrenvresp")
    private String solhrenvresp;
    @Size(max = 1)
    @Column(name = "soljustdevresp")
    private String soljustdevresp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solrespaceita")
    private int solrespaceita;
    @Size(max = 1)
    @Column(name = "solavalativa")
    private String solavalativa;
    @Size(max = 10)
    @Column(name = "soldtavalin")
    private String soldtavalin;
    @Size(max = 8)
    @Column(name = "solhravalin")
    private String solhravalin;
    @Column(name = "solsatisfcod")
    private Integer solsatisfcod;
    @Size(max = 18)
    @Column(name = "solsatisf")
    private String solsatisf;
    @Column(name = "solclassifcod")
    private Integer solclassifcod;
    @Size(max = 20)
    @Column(name = "solclassif")
    private String solclassif;
    @Column(name = "solevitrefcod")
    private Integer solevitrefcod;
    @Size(max = 3)
    @Column(name = "solevitref")
    private String solevitref;
    @Column(name = "solindrefcod")
    private Integer solindrefcod;
    @Size(max = 3)
    @Column(name = "solindref")
    private String solindref;
    @Size(max = 944)
    @Column(name = "solcritsug")
    private String solcritsug;
    @Size(max = 1)
    @Column(name = "respativa")
    private String respativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solatraso")
    private int solatraso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solatrasada")
    private int solatrasada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solsitcod")
    private int solsitcod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 42)
    @Column(name = "solsit")
    private String solsit;
    @Size(max = 10)
    @Column(name = "soldtsteleit")
    private String soldtsteleit;
    @Size(max = 10)
    @Column(name = "soldtavalfim")
    private String soldtavalfim;
    @Size(max = 7)
    @Column(name = "solavalfim")
    private String solavalfim;
    @Size(max = 8)
    @Column(name = "solhravalfim")
    private String solhravalfim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solprecomp")
    private int solprecomp;
    @Column(name = "soltpo1")
    private Integer soltpo1;
    @Column(name = "soltpo2")
    private Integer soltpo2;
    @Column(name = "soltpo3")
    private Integer soltpo3;
    @Column(name = "soltpo4")
    private Integer soltpo4;
    @Column(name = "soltpo5")
    private Integer soltpo5;
    @Column(name = "soltpo6")
    private Integer soltpo6;
    @Column(name = "soltresp")
    private Integer soltresp;
    @Column(name = "soltaval")
    private Integer soltaval;
    @Column(name = "soltreg")
    private Integer soltreg;

    public Processos() {
    }

    public Processos(Integer solcod) {
        this.solcod = solcod;
    }

    public Processos(Integer solcod, int solservcod, String solserv, int soltipocod, String soltipo, String soldtabertura, String solhorabert, int solativo, int solpacespcod, String solpacespec, String soldescricao, int stepessoacod, int steativopes, int stecod, int steativo, String stenome, String steemail, String stecpf, int steleingcod, String steleing, int steescolcod, String steescol, int steprofiscod, String steprofis, int steespeccod, String steespec, int steativoesp, int stefamcbocod, String stefamcbo, int stecbocod, String stecbo, int steeqcod, String steequipe, int steativoeq, int steunidcod, String steunid, int stecnesunid, int steativounid, int stetipounidcod, String stetipounid, String stesetorunid, int stetpativounid, int stemuniccod, String stemunic, String steuf, int steativouf, String stesiguf, int steufcod, int steativomun, int steregcod, String stereg, int steonu, String stepais, String stesigpais, String steinst, int steinstcod, String stetipoinst, int stemuninstcod, String stemuninst, int stemunsativoinst, int steonuinst, String stepaisinst, String stepaissiginst, int stereginstcod, String stereginst, int steufinstcod, String steufinst, String steufsiginst, int steufativoinst, int stenucleocod, String stenucleo, String stetiponuc, int steativonuc, int steufativonuc, int stecepnuc, int stemunnuccod, String stemunnuc, int stemunativonuc, int steufnuccod, String steufnuc, String steufsignuc, int stergaonuccod, String stergaonuc, String stepaisnuc, int steonunuccod, String stepaissignuc, int steconvcod, String steconv, int steativoconv, int stecoordcod, String stecoord, String steprovab, int steprovabcod, int stemmdcod, String stemmd, int stemcasacod, String stemcasa, String solregsitcod, String solregsit, int regaceite, int regativoesp, int regativonuc, int regativoconv, int consaceite, int consativoconv, int solrepet, int soljanecami, int solrespaceita, int solatraso, int solatrasada, int solsitcod, String solsit, int solprecomp) {
        this.solcod = solcod;
        this.solservcod = solservcod;
        this.solserv = solserv;
        this.soltipocod = soltipocod;
        this.soltipo = soltipo;
        this.soldtabertura = soldtabertura;
        this.solhorabert = solhorabert;
        this.solativo = solativo;
        this.solpacespcod = solpacespcod;
        this.solpacespec = solpacespec;
        this.soldescricao = soldescricao;
        this.stepessoacod = stepessoacod;
        this.steativopes = steativopes;
        this.stecod = stecod;
        this.steativo = steativo;
        this.stenome = stenome;
        this.steemail = steemail;
        this.stecpf = stecpf;
        this.steleingcod = steleingcod;
        this.steleing = steleing;
        this.steescolcod = steescolcod;
        this.steescol = steescol;
        this.steprofiscod = steprofiscod;
        this.steprofis = steprofis;
        this.steespeccod = steespeccod;
        this.steespec = steespec;
        this.steativoesp = steativoesp;
        this.stefamcbocod = stefamcbocod;
        this.stefamcbo = stefamcbo;
        this.stecbocod = stecbocod;
        this.stecbo = stecbo;
        this.steeqcod = steeqcod;
        this.steequipe = steequipe;
        this.steativoeq = steativoeq;
        this.steunidcod = steunidcod;
        this.steunid = steunid;
        this.stecnesunid = stecnesunid;
        this.steativounid = steativounid;
        this.stetipounidcod = stetipounidcod;
        this.stetipounid = stetipounid;
        this.stesetorunid = stesetorunid;
        this.stetpativounid = stetpativounid;
        this.stemuniccod = stemuniccod;
        this.stemunic = stemunic;
        this.steuf = steuf;
        this.steativouf = steativouf;
        this.stesiguf = stesiguf;
        this.steufcod = steufcod;
        this.steativomun = steativomun;
        this.steregcod = steregcod;
        this.stereg = stereg;
        this.steonu = steonu;
        this.stepais = stepais;
        this.stesigpais = stesigpais;
        this.steinst = steinst;
        this.steinstcod = steinstcod;
        this.stetipoinst = stetipoinst;
        this.stemuninstcod = stemuninstcod;
        this.stemuninst = stemuninst;
        this.stemunsativoinst = stemunsativoinst;
        this.steonuinst = steonuinst;
        this.stepaisinst = stepaisinst;
        this.stepaissiginst = stepaissiginst;
        this.stereginstcod = stereginstcod;
        this.stereginst = stereginst;
        this.steufinstcod = steufinstcod;
        this.steufinst = steufinst;
        this.steufsiginst = steufsiginst;
        this.steufativoinst = steufativoinst;
        this.stenucleocod = stenucleocod;
        this.stenucleo = stenucleo;
        this.stetiponuc = stetiponuc;
        this.steativonuc = steativonuc;
        this.steufativonuc = steufativonuc;
        this.stecepnuc = stecepnuc;
        this.stemunnuccod = stemunnuccod;
        this.stemunnuc = stemunnuc;
        this.stemunativonuc = stemunativonuc;
        this.steufnuccod = steufnuccod;
        this.steufnuc = steufnuc;
        this.steufsignuc = steufsignuc;
        this.stergaonuccod = stergaonuccod;
        this.stergaonuc = stergaonuc;
        this.stepaisnuc = stepaisnuc;
        this.steonunuccod = steonunuccod;
        this.stepaissignuc = stepaissignuc;
        this.steconvcod = steconvcod;
        this.steconv = steconv;
        this.steativoconv = steativoconv;
        this.stecoordcod = stecoordcod;
        this.stecoord = stecoord;
        this.steprovab = steprovab;
        this.steprovabcod = steprovabcod;
        this.stemmdcod = stemmdcod;
        this.stemmd = stemmd;
        this.stemcasacod = stemcasacod;
        this.stemcasa = stemcasa;
        this.solregsitcod = solregsitcod;
        this.solregsit = solregsit;
        this.regaceite = regaceite;
        this.regativoesp = regativoesp;
        this.regativonuc = regativonuc;
        this.regativoconv = regativoconv;
        this.consaceite = consaceite;
        this.consativoconv = consativoconv;
        this.solrepet = solrepet;
        this.soljanecami = soljanecami;
        this.solrespaceita = solrespaceita;
        this.solatraso = solatraso;
        this.solatrasada = solatrasada;
        this.solsitcod = solsitcod;
        this.solsit = solsit;
        this.solprecomp = solprecomp;
    }

    public Integer getSolcod() {
        return solcod;
    }

    public void setSolcod(Integer solcod) {
        this.solcod = solcod;
    }

    public int getSolservcod() {
        return solservcod;
    }

    public void setSolservcod(int solservcod) {
        this.solservcod = solservcod;
    }

    public String getSolserv() {
        return solserv;
    }

    public void setSolserv(String solserv) {
        this.solserv = solserv;
    }

    public int getSoltipocod() {
        return soltipocod;
    }

    public void setSoltipocod(int soltipocod) {
        this.soltipocod = soltipocod;
    }

    public String getSoltipo() {
        return soltipo;
    }

    public void setSoltipo(String soltipo) {
        this.soltipo = soltipo;
    }

    public String getSoldtabertura() {
        return soldtabertura;
    }

    public void setSoldtabertura(String soldtabertura) {
        this.soldtabertura = soldtabertura;
    }

    public String getSolhorabert() {
        return solhorabert;
    }

    public void setSolhorabert(String solhorabert) {
        this.solhorabert = solhorabert;
    }

    public String getSoldtenvio() {
        return soldtenvio;
    }

    public void setSoldtenvio(String soldtenvio) {
        this.soldtenvio = soldtenvio;
    }

    public String getSolenvio() {
        return solenvio;
    }

    public void setSolenvio(String solenvio) {
        this.solenvio = solenvio;
    }

    public String getSolhoranevio() {
        return solhoranevio;
    }

    public void setSolhoranevio(String solhoranevio) {
        this.solhoranevio = solhoranevio;
    }

    public int getSolativo() {
        return solativo;
    }

    public void setSolativo(int solativo) {
        this.solativo = solativo;
    }

    public int getSolpacespcod() {
        return solpacespcod;
    }

    public void setSolpacespcod(int solpacespcod) {
        this.solpacespcod = solpacespcod;
    }

    public String getSolpacespec() {
        return solpacespec;
    }

    public void setSolpacespec(String solpacespec) {
        this.solpacespec = solpacespec;
    }

    public Integer getSolintencacod() {
        return solintencacod;
    }

    public void setSolintencacod(Integer solintencacod) {
        this.solintencacod = solintencacod;
    }

    public String getSolintencami() {
        return solintencami;
    }

    public void setSolintencami(String solintencami) {
        this.solintencami = solintencami;
    }

    public String getSoldescricao() {
        return soldescricao;
    }

    public void setSoldescricao(String soldescricao) {
        this.soldescricao = soldescricao;
    }

    public int getStepessoacod() {
        return stepessoacod;
    }

    public void setStepessoacod(int stepessoacod) {
        this.stepessoacod = stepessoacod;
    }

    public int getSteativopes() {
        return steativopes;
    }

    public void setSteativopes(int steativopes) {
        this.steativopes = steativopes;
    }

    public int getStecod() {
        return stecod;
    }

    public void setStecod(int stecod) {
        this.stecod = stecod;
    }

    public int getSteativo() {
        return steativo;
    }

    public void setSteativo(int steativo) {
        this.steativo = steativo;
    }

    public String getStenome() {
        return stenome;
    }

    public void setStenome(String stenome) {
        this.stenome = stenome;
    }

    public Integer getStesexocod() {
        return stesexocod;
    }

    public void setStesexocod(Integer stesexocod) {
        this.stesexocod = stesexocod;
    }

    public String getStesexo() {
        return stesexo;
    }

    public void setStesexo(String stesexo) {
        this.stesexo = stesexo;
    }

    public String getStedtnasc() {
        return stedtnasc;
    }

    public void setStedtnasc(String stedtnasc) {
        this.stedtnasc = stedtnasc;
    }

    public String getSteemail() {
        return steemail;
    }

    public void setSteemail(String steemail) {
        this.steemail = steemail;
    }

    public String getStecpf() {
        return stecpf;
    }

    public void setStecpf(String stecpf) {
        this.stecpf = stecpf;
    }

    public Integer getStecns() {
        return stecns;
    }

    public void setStecns(Integer stecns) {
        this.stecns = stecns;
    }

    public Integer getSteoutdoc() {
        return steoutdoc;
    }

    public void setSteoutdoc(Integer steoutdoc) {
        this.steoutdoc = steoutdoc;
    }

    public Integer getStecepres() {
        return stecepres;
    }

    public void setStecepres(Integer stecepres) {
        this.stecepres = stecepres;
    }

    public Integer getStemunrescod() {
        return stemunrescod;
    }

    public void setStemunrescod(Integer stemunrescod) {
        this.stemunrescod = stemunrescod;
    }

    public String getStemunres() {
        return stemunres;
    }

    public void setStemunres(String stemunres) {
        this.stemunres = stemunres;
    }

    public Integer getStemunatres() {
        return stemunatres;
    }

    public void setStemunatres(Integer stemunatres) {
        this.stemunatres = stemunatres;
    }

    public Integer getSteufrescod() {
        return steufrescod;
    }

    public void setSteufrescod(Integer steufrescod) {
        this.steufrescod = steufrescod;
    }

    public String getSteufres() {
        return steufres;
    }

    public void setSteufres(String steufres) {
        this.steufres = steufres;
    }

    public Integer getSteufativores() {
        return steufativores;
    }

    public void setSteufativores(Integer steufativores) {
        this.steufativores = steufativores;
    }

    public String getSteufsigres() {
        return steufsigres;
    }

    public void setSteufsigres(String steufsigres) {
        this.steufsigres = steufsigres;
    }

    public int getSteleingcod() {
        return steleingcod;
    }

    public void setSteleingcod(int steleingcod) {
        this.steleingcod = steleingcod;
    }

    public String getSteleing() {
        return steleing;
    }

    public void setSteleing(String steleing) {
        this.steleing = steleing;
    }

    public Integer getSteinglescod() {
        return steinglescod;
    }

    public void setSteinglescod(Integer steinglescod) {
        this.steinglescod = steinglescod;
    }

    public String getSteingles() {
        return steingles;
    }

    public void setSteingles(String steingles) {
        this.steingles = steingles;
    }

    public int getSteescolcod() {
        return steescolcod;
    }

    public void setSteescolcod(int steescolcod) {
        this.steescolcod = steescolcod;
    }

    public String getSteescol() {
        return steescol;
    }

    public void setSteescol(String steescol) {
        this.steescol = steescol;
    }

    public Integer getSteprofisreg() {
        return steprofisreg;
    }

    public void setSteprofisreg(Integer steprofisreg) {
        this.steprofisreg = steprofisreg;
    }

    public int getSteprofiscod() {
        return steprofiscod;
    }

    public void setSteprofiscod(int steprofiscod) {
        this.steprofiscod = steprofiscod;
    }

    public String getSteprofis() {
        return steprofis;
    }

    public void setSteprofis(String steprofis) {
        this.steprofis = steprofis;
    }

    public Integer getStehabprofis() {
        return stehabprofis;
    }

    public void setStehabprofis(Integer stehabprofis) {
        this.stehabprofis = stehabprofis;
    }

    public int getSteespeccod() {
        return steespeccod;
    }

    public void setSteespeccod(int steespeccod) {
        this.steespeccod = steespeccod;
    }

    public String getSteespec() {
        return steespec;
    }

    public void setSteespec(String steespec) {
        this.steespec = steespec;
    }

    public Integer getSteanoespec() {
        return steanoespec;
    }

    public void setSteanoespec(Integer steanoespec) {
        this.steanoespec = steanoespec;
    }

    public int getSteativoesp() {
        return steativoesp;
    }

    public void setSteativoesp(int steativoesp) {
        this.steativoesp = steativoesp;
    }

    public Integer getStetpespcod() {
        return stetpespcod;
    }

    public void setStetpespcod(Integer stetpespcod) {
        this.stetpespcod = stetpespcod;
    }

    public String getStetpesp() {
        return stetpesp;
    }

    public void setStetpesp(String stetpesp) {
        this.stetpesp = stetpesp;
    }

    public int getStefamcbocod() {
        return stefamcbocod;
    }

    public void setStefamcbocod(int stefamcbocod) {
        this.stefamcbocod = stefamcbocod;
    }

    public String getStefamcbo() {
        return stefamcbo;
    }

    public void setStefamcbo(String stefamcbo) {
        this.stefamcbo = stefamcbo;
    }

    public int getStecbocod() {
        return stecbocod;
    }

    public void setStecbocod(int stecbocod) {
        this.stecbocod = stecbocod;
    }

    public String getStecbo() {
        return stecbo;
    }

    public void setStecbo(String stecbo) {
        this.stecbo = stecbo;
    }

    public int getSteeqcod() {
        return steeqcod;
    }

    public void setSteeqcod(int steeqcod) {
        this.steeqcod = steeqcod;
    }

    public String getSteequipe() {
        return steequipe;
    }

    public void setSteequipe(String steequipe) {
        this.steequipe = steequipe;
    }

    public Integer getSteine() {
        return steine;
    }

    public void setSteine(Integer steine) {
        this.steine = steine;
    }

    public int getSteativoeq() {
        return steativoeq;
    }

    public void setSteativoeq(int steativoeq) {
        this.steativoeq = steativoeq;
    }

    public Integer getStetipoeqcod() {
        return stetipoeqcod;
    }

    public void setStetipoeqcod(Integer stetipoeqcod) {
        this.stetipoeqcod = stetipoeqcod;
    }

    public String getStetipoeq() {
        return stetipoeq;
    }

    public void setStetipoeq(String stetipoeq) {
        this.stetipoeq = stetipoeq;
    }

    public Integer getStetpativoeq() {
        return stetpativoeq;
    }

    public void setStetpativoeq(Integer stetpativoeq) {
        this.stetpativoeq = stetpativoeq;
    }

    public int getSteunidcod() {
        return steunidcod;
    }

    public void setSteunidcod(int steunidcod) {
        this.steunidcod = steunidcod;
    }

    public String getSteunid() {
        return steunid;
    }

    public void setSteunid(String steunid) {
        this.steunid = steunid;
    }

    public int getStecnesunid() {
        return stecnesunid;
    }

    public void setStecnesunid(int stecnesunid) {
        this.stecnesunid = stecnesunid;
    }

    public int getSteativounid() {
        return steativounid;
    }

    public void setSteativounid(int steativounid) {
        this.steativounid = steativounid;
    }

    public int getStetipounidcod() {
        return stetipounidcod;
    }

    public void setStetipounidcod(int stetipounidcod) {
        this.stetipounidcod = stetipounidcod;
    }

    public String getStetipounid() {
        return stetipounid;
    }

    public void setStetipounid(String stetipounid) {
        this.stetipounid = stetipounid;
    }

    public String getStesetorunid() {
        return stesetorunid;
    }

    public void setStesetorunid(String stesetorunid) {
        this.stesetorunid = stesetorunid;
    }

    public int getStetpativounid() {
        return stetpativounid;
    }

    public void setStetpativounid(int stetpativounid) {
        this.stetpativounid = stetpativounid;
    }

    public Integer getStecep() {
        return stecep;
    }

    public void setStecep(Integer stecep) {
        this.stecep = stecep;
    }

    public int getStemuniccod() {
        return stemuniccod;
    }

    public void setStemuniccod(int stemuniccod) {
        this.stemuniccod = stemuniccod;
    }

    public String getStemunic() {
        return stemunic;
    }

    public void setStemunic(String stemunic) {
        this.stemunic = stemunic;
    }

    public String getSteuf() {
        return steuf;
    }

    public void setSteuf(String steuf) {
        this.steuf = steuf;
    }

    public int getSteativouf() {
        return steativouf;
    }

    public void setSteativouf(int steativouf) {
        this.steativouf = steativouf;
    }

    public String getStesiguf() {
        return stesiguf;
    }

    public void setStesiguf(String stesiguf) {
        this.stesiguf = stesiguf;
    }

    public int getSteufcod() {
        return steufcod;
    }

    public void setSteufcod(int steufcod) {
        this.steufcod = steufcod;
    }

    public int getSteativomun() {
        return steativomun;
    }

    public void setSteativomun(int steativomun) {
        this.steativomun = steativomun;
    }

    public int getSteregcod() {
        return steregcod;
    }

    public void setSteregcod(int steregcod) {
        this.steregcod = steregcod;
    }

    public String getStereg() {
        return stereg;
    }

    public void setStereg(String stereg) {
        this.stereg = stereg;
    }

    public int getSteonu() {
        return steonu;
    }

    public void setSteonu(int steonu) {
        this.steonu = steonu;
    }

    public String getStepais() {
        return stepais;
    }

    public void setStepais(String stepais) {
        this.stepais = stepais;
    }

    public String getStesigpais() {
        return stesigpais;
    }

    public void setStesigpais(String stesigpais) {
        this.stesigpais = stesigpais;
    }

    public String getStenequs() {
        return stenequs;
    }

    public void setStenequs(String stenequs) {
        this.stenequs = stenequs;
    }

    public String getSteeqcomus() {
        return steeqcomus;
    }

    public void setSteeqcomus(String steeqcomus) {
        this.steeqcomus = steeqcomus;
    }

    public String getSteeqintus() {
        return steeqintus;
    }

    public void setSteeqintus(String steeqintus) {
        this.steeqintus = steeqintus;
    }

    public String getStenwebcus() {
        return stenwebcus;
    }

    public void setStenwebcus(String stenwebcus) {
        this.stenwebcus = stenwebcus;
    }

    public String getStenmicus() {
        return stenmicus;
    }

    public void setStenmicus(String stenmicus) {
        this.stenmicus = stenmicus;
    }

    public String getStensomus() {
        return stensomus;
    }

    public void setStensomus(String stensomus) {
        this.stensomus = stensomus;
    }

    public String getStenfoneus() {
        return stenfoneus;
    }

    public void setStenfoneus(String stenfoneus) {
        this.stenfoneus = stenfoneus;
    }

    public String getSteintadequs() {
        return steintadequs;
    }

    public void setSteintadequs(String steintadequs) {
        this.steintadequs = steintadequs;
    }

    public String getSteinst() {
        return steinst;
    }

    public void setSteinst(String steinst) {
        this.steinst = steinst;
    }

    public int getSteinstcod() {
        return steinstcod;
    }

    public void setSteinstcod(int steinstcod) {
        this.steinstcod = steinstcod;
    }

    public String getStetipoinst() {
        return stetipoinst;
    }

    public void setStetipoinst(String stetipoinst) {
        this.stetipoinst = stetipoinst;
    }

    public String getStecepinst() {
        return stecepinst;
    }

    public void setStecepinst(String stecepinst) {
        this.stecepinst = stecepinst;
    }

    public int getStemuninstcod() {
        return stemuninstcod;
    }

    public void setStemuninstcod(int stemuninstcod) {
        this.stemuninstcod = stemuninstcod;
    }

    public String getStemuninst() {
        return stemuninst;
    }

    public void setStemuninst(String stemuninst) {
        this.stemuninst = stemuninst;
    }

    public int getStemunsativoinst() {
        return stemunsativoinst;
    }

    public void setStemunsativoinst(int stemunsativoinst) {
        this.stemunsativoinst = stemunsativoinst;
    }

    public int getSteonuinst() {
        return steonuinst;
    }

    public void setSteonuinst(int steonuinst) {
        this.steonuinst = steonuinst;
    }

    public String getStepaisinst() {
        return stepaisinst;
    }

    public void setStepaisinst(String stepaisinst) {
        this.stepaisinst = stepaisinst;
    }

    public String getStepaissiginst() {
        return stepaissiginst;
    }

    public void setStepaissiginst(String stepaissiginst) {
        this.stepaissiginst = stepaissiginst;
    }

    public int getStereginstcod() {
        return stereginstcod;
    }

    public void setStereginstcod(int stereginstcod) {
        this.stereginstcod = stereginstcod;
    }

    public String getStereginst() {
        return stereginst;
    }

    public void setStereginst(String stereginst) {
        this.stereginst = stereginst;
    }

    public int getSteufinstcod() {
        return steufinstcod;
    }

    public void setSteufinstcod(int steufinstcod) {
        this.steufinstcod = steufinstcod;
    }

    public String getSteufinst() {
        return steufinst;
    }

    public void setSteufinst(String steufinst) {
        this.steufinst = steufinst;
    }

    public String getSteufsiginst() {
        return steufsiginst;
    }

    public void setSteufsiginst(String steufsiginst) {
        this.steufsiginst = steufsiginst;
    }

    public int getSteufativoinst() {
        return steufativoinst;
    }

    public void setSteufativoinst(int steufativoinst) {
        this.steufativoinst = steufativoinst;
    }

    public int getStenucleocod() {
        return stenucleocod;
    }

    public void setStenucleocod(int stenucleocod) {
        this.stenucleocod = stenucleocod;
    }

    public String getStenucleo() {
        return stenucleo;
    }

    public void setStenucleo(String stenucleo) {
        this.stenucleo = stenucleo;
    }

    public String getStetiponuc() {
        return stetiponuc;
    }

    public void setStetiponuc(String stetiponuc) {
        this.stetiponuc = stetiponuc;
    }

    public int getSteativonuc() {
        return steativonuc;
    }

    public void setSteativonuc(int steativonuc) {
        this.steativonuc = steativonuc;
    }

    public int getSteufativonuc() {
        return steufativonuc;
    }

    public void setSteufativonuc(int steufativonuc) {
        this.steufativonuc = steufativonuc;
    }

    public int getStecepnuc() {
        return stecepnuc;
    }

    public void setStecepnuc(int stecepnuc) {
        this.stecepnuc = stecepnuc;
    }

    public int getStemunnuccod() {
        return stemunnuccod;
    }

    public void setStemunnuccod(int stemunnuccod) {
        this.stemunnuccod = stemunnuccod;
    }

    public String getStemunnuc() {
        return stemunnuc;
    }

    public void setStemunnuc(String stemunnuc) {
        this.stemunnuc = stemunnuc;
    }

    public int getStemunativonuc() {
        return stemunativonuc;
    }

    public void setStemunativonuc(int stemunativonuc) {
        this.stemunativonuc = stemunativonuc;
    }

    public int getSteufnuccod() {
        return steufnuccod;
    }

    public void setSteufnuccod(int steufnuccod) {
        this.steufnuccod = steufnuccod;
    }

    public String getSteufnuc() {
        return steufnuc;
    }

    public void setSteufnuc(String steufnuc) {
        this.steufnuc = steufnuc;
    }

    public String getSteufsignuc() {
        return steufsignuc;
    }

    public void setSteufsignuc(String steufsignuc) {
        this.steufsignuc = steufsignuc;
    }

    public int getStergaonuccod() {
        return stergaonuccod;
    }

    public void setStergaonuccod(int stergaonuccod) {
        this.stergaonuccod = stergaonuccod;
    }

    public String getStergaonuc() {
        return stergaonuc;
    }

    public void setStergaonuc(String stergaonuc) {
        this.stergaonuc = stergaonuc;
    }

    public String getStepaisnuc() {
        return stepaisnuc;
    }

    public void setStepaisnuc(String stepaisnuc) {
        this.stepaisnuc = stepaisnuc;
    }

    public int getSteonunuccod() {
        return steonunuccod;
    }

    public void setSteonunuccod(int steonunuccod) {
        this.steonunuccod = steonunuccod;
    }

    public String getStepaissignuc() {
        return stepaissignuc;
    }

    public void setStepaissignuc(String stepaissignuc) {
        this.stepaissignuc = stepaissignuc;
    }

    public int getSteconvcod() {
        return steconvcod;
    }

    public void setSteconvcod(int steconvcod) {
        this.steconvcod = steconvcod;
    }

    public String getSteconv() {
        return steconv;
    }

    public void setSteconv(String steconv) {
        this.steconv = steconv;
    }

    public int getSteativoconv() {
        return steativoconv;
    }

    public void setSteativoconv(int steativoconv) {
        this.steativoconv = steativoconv;
    }

    public int getStecoordcod() {
        return stecoordcod;
    }

    public void setStecoordcod(int stecoordcod) {
        this.stecoordcod = stecoordcod;
    }

    public String getStecoord() {
        return stecoord;
    }

    public void setStecoord(String stecoord) {
        this.stecoord = stecoord;
    }

    public String getSteprovab() {
        return steprovab;
    }

    public void setSteprovab(String steprovab) {
        this.steprovab = steprovab;
    }

    public int getSteprovabcod() {
        return steprovabcod;
    }

    public void setSteprovabcod(int steprovabcod) {
        this.steprovabcod = steprovabcod;
    }

    public int getStemmdcod() {
        return stemmdcod;
    }

    public void setStemmdcod(int stemmdcod) {
        this.stemmdcod = stemmdcod;
    }

    public String getStemmd() {
        return stemmd;
    }

    public void setStemmd(String stemmd) {
        this.stemmd = stemmd;
    }

    public int getStemcasacod() {
        return stemcasacod;
    }

    public void setStemcasacod(int stemcasacod) {
        this.stemcasacod = stemcasacod;
    }

    public String getStemcasa() {
        return stemcasa;
    }

    public void setStemcasa(String stemcasa) {
        this.stemcasa = stemcasa;
    }

    public String getRegdtreceb() {
        return regdtreceb;
    }

    public void setRegdtreceb(String regdtreceb) {
        this.regdtreceb = regdtreceb;
    }

    public String getReghorareceb() {
        return reghorareceb;
    }

    public void setReghorareceb(String reghorareceb) {
        this.reghorareceb = reghorareceb;
    }

    public String getSolacreg() {
        return solacreg;
    }

    public void setSolacreg(String solacreg) {
        this.solacreg = solacreg;
    }

    public String getSolregsitcod() {
        return solregsitcod;
    }

    public void setSolregsitcod(String solregsitcod) {
        this.solregsitcod = solregsitcod;
    }

    public String getSolregsit() {
        return solregsit;
    }

    public void setSolregsit(String solregsit) {
        this.solregsit = solregsit;
    }

    public int getRegaceite() {
        return regaceite;
    }

    public void setRegaceite(int regaceite) {
        this.regaceite = regaceite;
    }

    public String getRegdtdevol() {
        return regdtdevol;
    }

    public void setRegdtdevol(String regdtdevol) {
        this.regdtdevol = regdtdevol;
    }

    public String getRegnegativa() {
        return regnegativa;
    }

    public void setRegnegativa(String regnegativa) {
        this.regnegativa = regnegativa;
    }

    public Integer getRegpessoacod() {
        return regpessoacod;
    }

    public void setRegpessoacod(Integer regpessoacod) {
        this.regpessoacod = regpessoacod;
    }

    public Integer getRegativopes() {
        return regativopes;
    }

    public void setRegativopes(Integer regativopes) {
        this.regativopes = regativopes;
    }

    public Integer getRegcod() {
        return regcod;
    }

    public void setRegcod(Integer regcod) {
        this.regcod = regcod;
    }

    public Integer getRegativo() {
        return regativo;
    }

    public void setRegativo(Integer regativo) {
        this.regativo = regativo;
    }

    public String getRegnome() {
        return regnome;
    }

    public void setRegnome(String regnome) {
        this.regnome = regnome;
    }

    public String getRegsexo() {
        return regsexo;
    }

    public void setRegsexo(String regsexo) {
        this.regsexo = regsexo;
    }

    public Integer getRegsexocod() {
        return regsexocod;
    }

    public void setRegsexocod(Integer regsexocod) {
        this.regsexocod = regsexocod;
    }

    public String getRegdtnasc() {
        return regdtnasc;
    }

    public void setRegdtnasc(String regdtnasc) {
        this.regdtnasc = regdtnasc;
    }

    public String getRegemail() {
        return regemail;
    }

    public void setRegemail(String regemail) {
        this.regemail = regemail;
    }

    public String getRegcpf() {
        return regcpf;
    }

    public void setRegcpf(String regcpf) {
        this.regcpf = regcpf;
    }

    public String getRegcns() {
        return regcns;
    }

    public void setRegcns(String regcns) {
        this.regcns = regcns;
    }

    public String getRegmunrescod() {
        return regmunrescod;
    }

    public void setRegmunrescod(String regmunrescod) {
        this.regmunrescod = regmunrescod;
    }

    public String getRegmunres() {
        return regmunres;
    }

    public void setRegmunres(String regmunres) {
        this.regmunres = regmunres;
    }

    public Integer getRegleingcod() {
        return regleingcod;
    }

    public void setRegleingcod(Integer regleingcod) {
        this.regleingcod = regleingcod;
    }

    public String getRegleing() {
        return regleing;
    }

    public void setRegleing(String regleing) {
        this.regleing = regleing;
    }

    public Integer getReginglescod() {
        return reginglescod;
    }

    public void setReginglescod(Integer reginglescod) {
        this.reginglescod = reginglescod;
    }

    public String getRegingles() {
        return regingles;
    }

    public void setRegingles(String regingles) {
        this.regingles = regingles;
    }

    public Integer getRegescolcod() {
        return regescolcod;
    }

    public void setRegescolcod(Integer regescolcod) {
        this.regescolcod = regescolcod;
    }

    public String getRegescol() {
        return regescol;
    }

    public void setRegescol(String regescol) {
        this.regescol = regescol;
    }

    public String getRegprofisreg() {
        return regprofisreg;
    }

    public void setRegprofisreg(String regprofisreg) {
        this.regprofisreg = regprofisreg;
    }

    public Integer getRegprofiscod() {
        return regprofiscod;
    }

    public void setRegprofiscod(Integer regprofiscod) {
        this.regprofiscod = regprofiscod;
    }

    public String getRegprofis() {
        return regprofis;
    }

    public void setRegprofis(String regprofis) {
        this.regprofis = regprofis;
    }

    public String getReghabprofis() {
        return reghabprofis;
    }

    public void setReghabprofis(String reghabprofis) {
        this.reghabprofis = reghabprofis;
    }

    public Integer getRegespeccod() {
        return regespeccod;
    }

    public void setRegespeccod(Integer regespeccod) {
        this.regespeccod = regespeccod;
    }

    public int getRegativoesp() {
        return regativoesp;
    }

    public void setRegativoesp(int regativoesp) {
        this.regativoesp = regativoesp;
    }

    public String getRegespec() {
        return regespec;
    }

    public void setRegespec(String regespec) {
        this.regespec = regespec;
    }

    public String getReganoespec() {
        return reganoespec;
    }

    public void setReganoespec(String reganoespec) {
        this.reganoespec = reganoespec;
    }

    public Integer getRegtpespcod() {
        return regtpespcod;
    }

    public void setRegtpespcod(Integer regtpespcod) {
        this.regtpespcod = regtpespcod;
    }

    public String getRegtpesp() {
        return regtpesp;
    }

    public void setRegtpesp(String regtpesp) {
        this.regtpesp = regtpesp;
    }

    public Integer getRegfamcbocod() {
        return regfamcbocod;
    }

    public void setRegfamcbocod(Integer regfamcbocod) {
        this.regfamcbocod = regfamcbocod;
    }

    public String getRegfamcbo() {
        return regfamcbo;
    }

    public void setRegfamcbo(String regfamcbo) {
        this.regfamcbo = regfamcbo;
    }

    public Integer getRegcbocod() {
        return regcbocod;
    }

    public void setRegcbocod(Integer regcbocod) {
        this.regcbocod = regcbocod;
    }

    public String getRegcbo() {
        return regcbo;
    }

    public void setRegcbo(String regcbo) {
        this.regcbo = regcbo;
    }

    public Integer getRegnucleocod() {
        return regnucleocod;
    }

    public void setRegnucleocod(Integer regnucleocod) {
        this.regnucleocod = regnucleocod;
    }

    public int getRegativonuc() {
        return regativonuc;
    }

    public void setRegativonuc(int regativonuc) {
        this.regativonuc = regativonuc;
    }

    public String getRegnucleo() {
        return regnucleo;
    }

    public void setRegnucleo(String regnucleo) {
        this.regnucleo = regnucleo;
    }

    public Integer getRegnucatucod() {
        return regnucatucod;
    }

    public void setRegnucatucod(Integer regnucatucod) {
        this.regnucatucod = regnucatucod;
    }

    public String getRegnucatu() {
        return regnucatu;
    }

    public void setRegnucatu(String regnucatu) {
        this.regnucatu = regnucatu;
    }

    public Integer getRegmunnuccod() {
        return regmunnuccod;
    }

    public void setRegmunnuccod(Integer regmunnuccod) {
        this.regmunnuccod = regmunnuccod;
    }

    public String getRegmunnuc() {
        return regmunnuc;
    }

    public void setRegmunnuc(String regmunnuc) {
        this.regmunnuc = regmunnuc;
    }

    public String getReguf() {
        return reguf;
    }

    public void setReguf(String reguf) {
        this.reguf = reguf;
    }

    public Integer getRegonunuccod() {
        return regonunuccod;
    }

    public void setRegonunuccod(Integer regonunuccod) {
        this.regonunuccod = regonunuccod;
    }

    public String getRegpaisnuc() {
        return regpaisnuc;
    }

    public void setRegpaisnuc(String regpaisnuc) {
        this.regpaisnuc = regpaisnuc;
    }

    public String getRegsignuc() {
        return regsignuc;
    }

    public void setRegsignuc(String regsignuc) {
        this.regsignuc = regsignuc;
    }

    public Integer getRegconvcod() {
        return regconvcod;
    }

    public void setRegconvcod(Integer regconvcod) {
        this.regconvcod = regconvcod;
    }

    public String getRegconv() {
        return regconv;
    }

    public void setRegconv(String regconv) {
        this.regconv = regconv;
    }

    public int getRegativoconv() {
        return regativoconv;
    }

    public void setRegativoconv(int regativoconv) {
        this.regativoconv = regativoconv;
    }

    public String getRegchoraria() {
        return regchoraria;
    }

    public void setRegchoraria(String regchoraria) {
        this.regchoraria = regchoraria;
    }

    public String getRegciap1cod() {
        return regciap1cod;
    }

    public void setRegciap1cod(String regciap1cod) {
        this.regciap1cod = regciap1cod;
    }

    public String getRegciap1() {
        return regciap1;
    }

    public void setRegciap1(String regciap1) {
        this.regciap1 = regciap1;
    }

    public String getRegciap2cod() {
        return regciap2cod;
    }

    public void setRegciap2cod(String regciap2cod) {
        this.regciap2cod = regciap2cod;
    }

    public String getRegciap2() {
        return regciap2;
    }

    public void setRegciap2(String regciap2) {
        this.regciap2 = regciap2;
    }

    public String getRegciap3cod() {
        return regciap3cod;
    }

    public void setRegciap3cod(String regciap3cod) {
        this.regciap3cod = regciap3cod;
    }

    public String getRegciap3() {
        return regciap3;
    }

    public void setRegciap3(String regciap3) {
        this.regciap3 = regciap3;
    }

    public String getRegcid1cod() {
        return regcid1cod;
    }

    public void setRegcid1cod(String regcid1cod) {
        this.regcid1cod = regcid1cod;
    }

    public String getRegcid1() {
        return regcid1;
    }

    public void setRegcid1(String regcid1) {
        this.regcid1 = regcid1;
    }

    public String getRegcid2cod() {
        return regcid2cod;
    }

    public void setRegcid2cod(String regcid2cod) {
        this.regcid2cod = regcid2cod;
    }

    public String getRegcid2() {
        return regcid2;
    }

    public void setRegcid2(String regcid2) {
        this.regcid2 = regcid2;
    }

    public String getRegagvideo() {
        return regagvideo;
    }

    public void setRegagvideo(String regagvideo) {
        this.regagvideo = regagvideo;
    }

    public String getRegdtagendou() {
        return regdtagendou;
    }

    public void setRegdtagendou(String regdtagendou) {
        this.regdtagendou = regdtagendou;
    }

    public String getRegdtconfagend() {
        return regdtconfagend;
    }

    public void setRegdtconfagend(String regdtconfagend) {
        this.regdtconfagend = regdtconfagend;
    }

    public String getRegdtcanage() {
        return regdtcanage;
    }

    public void setRegdtcanage(String regdtcanage) {
        this.regdtcanage = regdtcanage;
    }

    public String getRegdtreag() {
        return regdtreag;
    }

    public void setRegdtreag(String regdtreag) {
        this.regdtreag = regdtreag;
    }

    public String getMonnome() {
        return monnome;
    }

    public void setMonnome(String monnome) {
        this.monnome = monnome;
    }

    public String getRegdtenvio() {
        return regdtenvio;
    }

    public void setRegdtenvio(String regdtenvio) {
        this.regdtenvio = regdtenvio;
    }

    public String getReghoraenvio() {
        return reghoraenvio;
    }

    public void setReghoraenvio(String reghoraenvio) {
        this.reghoraenvio = reghoraenvio;
    }

    public String getRegenvio() {
        return regenvio;
    }

    public void setRegenvio(String regenvio) {
        this.regenvio = regenvio;
    }

    public String getConsdtacresp() {
        return consdtacresp;
    }

    public void setConsdtacresp(String consdtacresp) {
        this.consdtacresp = consdtacresp;
    }

    public String getConshracresp() {
        return conshracresp;
    }

    public void setConshracresp(String conshracresp) {
        this.conshracresp = conshracresp;
    }

    public String getConsdtdevol() {
        return consdtdevol;
    }

    public void setConsdtdevol(String consdtdevol) {
        this.consdtdevol = consdtdevol;
    }

    public Integer getConspessoacos() {
        return conspessoacos;
    }

    public void setConspessoacos(Integer conspessoacos) {
        this.conspessoacos = conspessoacos;
    }

    public Integer getConsativopes() {
        return consativopes;
    }

    public void setConsativopes(Integer consativopes) {
        this.consativopes = consativopes;
    }

    public Integer getConscod() {
        return conscod;
    }

    public void setConscod(Integer conscod) {
        this.conscod = conscod;
    }

    public Integer getConsativo() {
        return consativo;
    }

    public void setConsativo(Integer consativo) {
        this.consativo = consativo;
    }

    public int getConsaceite() {
        return consaceite;
    }

    public void setConsaceite(int consaceite) {
        this.consaceite = consaceite;
    }

    public String getConsnome() {
        return consnome;
    }

    public void setConsnome(String consnome) {
        this.consnome = consnome;
    }

    public String getConssexo() {
        return conssexo;
    }

    public void setConssexo(String conssexo) {
        this.conssexo = conssexo;
    }

    public Integer getConssexocod() {
        return conssexocod;
    }

    public void setConssexocod(Integer conssexocod) {
        this.conssexocod = conssexocod;
    }

    public String getConsdtnasc() {
        return consdtnasc;
    }

    public void setConsdtnasc(String consdtnasc) {
        this.consdtnasc = consdtnasc;
    }

    public String getConsemail() {
        return consemail;
    }

    public void setConsemail(String consemail) {
        this.consemail = consemail;
    }

    public String getConscpf() {
        return conscpf;
    }

    public void setConscpf(String conscpf) {
        this.conscpf = conscpf;
    }

    public Integer getConscns() {
        return conscns;
    }

    public void setConscns(Integer conscns) {
        this.conscns = conscns;
    }

    public Integer getConsmunrescod() {
        return consmunrescod;
    }

    public void setConsmunrescod(Integer consmunrescod) {
        this.consmunrescod = consmunrescod;
    }

    public String getConsmunres() {
        return consmunres;
    }

    public void setConsmunres(String consmunres) {
        this.consmunres = consmunres;
    }

    public Integer getConsmuniccod() {
        return consmuniccod;
    }

    public void setConsmuniccod(Integer consmuniccod) {
        this.consmuniccod = consmuniccod;
    }

    public String getConsmunic() {
        return consmunic;
    }

    public void setConsmunic(String consmunic) {
        this.consmunic = consmunic;
    }

    public String getConsuf() {
        return consuf;
    }

    public void setConsuf(String consuf) {
        this.consuf = consuf;
    }

    public Integer getConsleingcod() {
        return consleingcod;
    }

    public void setConsleingcod(Integer consleingcod) {
        this.consleingcod = consleingcod;
    }

    public String getConsleing() {
        return consleing;
    }

    public void setConsleing(String consleing) {
        this.consleing = consleing;
    }

    public Integer getConsinglescod() {
        return consinglescod;
    }

    public void setConsinglescod(Integer consinglescod) {
        this.consinglescod = consinglescod;
    }

    public String getConsingles() {
        return consingles;
    }

    public void setConsingles(String consingles) {
        this.consingles = consingles;
    }

    public Integer getConsescolcod() {
        return consescolcod;
    }

    public void setConsescolcod(Integer consescolcod) {
        this.consescolcod = consescolcod;
    }

    public String getConsescol() {
        return consescol;
    }

    public void setConsescol(String consescol) {
        this.consescol = consescol;
    }

    public Integer getConsprofisreg() {
        return consprofisreg;
    }

    public void setConsprofisreg(Integer consprofisreg) {
        this.consprofisreg = consprofisreg;
    }

    public Integer getConsprofiscod() {
        return consprofiscod;
    }

    public void setConsprofiscod(Integer consprofiscod) {
        this.consprofiscod = consprofiscod;
    }

    public String getConsprofis() {
        return consprofis;
    }

    public void setConsprofis(String consprofis) {
        this.consprofis = consprofis;
    }

    public Integer getConshabprofis() {
        return conshabprofis;
    }

    public void setConshabprofis(Integer conshabprofis) {
        this.conshabprofis = conshabprofis;
    }

    public Integer getConsespeccod() {
        return consespeccod;
    }

    public void setConsespeccod(Integer consespeccod) {
        this.consespeccod = consespeccod;
    }

    public String getConsespec() {
        return consespec;
    }

    public void setConsespec(String consespec) {
        this.consespec = consespec;
    }

    public Integer getConstpespcod() {
        return constpespcod;
    }

    public void setConstpespcod(Integer constpespcod) {
        this.constpespcod = constpespcod;
    }

    public String getConstpesp() {
        return constpesp;
    }

    public void setConstpesp(String constpesp) {
        this.constpesp = constpesp;
    }

    public Integer getConsanoespec() {
        return consanoespec;
    }

    public void setConsanoespec(Integer consanoespec) {
        this.consanoespec = consanoespec;
    }

    public Integer getConsativoesp() {
        return consativoesp;
    }

    public void setConsativoesp(Integer consativoesp) {
        this.consativoesp = consativoesp;
    }

    public Integer getConsfamcbocod() {
        return consfamcbocod;
    }

    public void setConsfamcbocod(Integer consfamcbocod) {
        this.consfamcbocod = consfamcbocod;
    }

    public String getConsfamcbo() {
        return consfamcbo;
    }

    public void setConsfamcbo(String consfamcbo) {
        this.consfamcbo = consfamcbo;
    }

    public Integer getConscbocod() {
        return conscbocod;
    }

    public void setConscbocod(Integer conscbocod) {
        this.conscbocod = conscbocod;
    }

    public String getConscbo() {
        return conscbo;
    }

    public void setConscbo(String conscbo) {
        this.conscbo = conscbo;
    }

    public Integer getConsnucleocod() {
        return consnucleocod;
    }

    public void setConsnucleocod(Integer consnucleocod) {
        this.consnucleocod = consnucleocod;
    }

    public String getConsnucleo() {
        return consnucleo;
    }

    public void setConsnucleo(String consnucleo) {
        this.consnucleo = consnucleo;
    }

    public Boolean getConsativonuc() {
        return consativonuc;
    }

    public void setConsativonuc(Boolean consativonuc) {
        this.consativonuc = consativonuc;
    }

    public Integer getConsnucatucod() {
        return consnucatucod;
    }

    public void setConsnucatucod(Integer consnucatucod) {
        this.consnucatucod = consnucatucod;
    }

    public String getConsnucatu() {
        return consnucatu;
    }

    public void setConsnucatu(String consnucatu) {
        this.consnucatu = consnucatu;
    }

    public Integer getConsmunnuccod() {
        return consmunnuccod;
    }

    public void setConsmunnuccod(Integer consmunnuccod) {
        this.consmunnuccod = consmunnuccod;
    }

    public String getConsmunnuc() {
        return consmunnuc;
    }

    public void setConsmunnuc(String consmunnuc) {
        this.consmunnuc = consmunnuc;
    }

    public Integer getConsonunuccod() {
        return consonunuccod;
    }

    public void setConsonunuccod(Integer consonunuccod) {
        this.consonunuccod = consonunuccod;
    }

    public String getConspaisnuc() {
        return conspaisnuc;
    }

    public void setConspaisnuc(String conspaisnuc) {
        this.conspaisnuc = conspaisnuc;
    }

    public String getConssignuc() {
        return conssignuc;
    }

    public void setConssignuc(String conssignuc) {
        this.conssignuc = conssignuc;
    }

    public Integer getConsconvcod() {
        return consconvcod;
    }

    public void setConsconvcod(Integer consconvcod) {
        this.consconvcod = consconvcod;
    }

    public String getConsconv() {
        return consconv;
    }

    public void setConsconv(String consconv) {
        this.consconv = consconv;
    }

    public int getConsativoconv() {
        return consativoconv;
    }

    public void setConsativoconv(int consativoconv) {
        this.consativoconv = consativoconv;
    }

    public String getConshorasres() {
        return conshorasres;
    }

    public void setConshorasres(String conshorasres) {
        this.conshorasres = conshorasres;
    }

    public String getConschoraria() {
        return conschoraria;
    }

    public void setConschoraria(String conschoraria) {
        this.conschoraria = conschoraria;
    }

    public int getSolrepet() {
        return solrepet;
    }

    public void setSolrepet(int solrepet) {
        this.solrepet = solrepet;
    }

    public String getSolresposta() {
        return solresposta;
    }

    public void setSolresposta(String solresposta) {
        this.solresposta = solresposta;
    }

    public String getSolcompresp() {
        return solcompresp;
    }

    public void setSolcompresp(String solcompresp) {
        this.solcompresp = solcompresp;
    }

    public String getSolcompcod() {
        return solcompcod;
    }

    public void setSolcompcod(String solcompcod) {
        this.solcompcod = solcompcod;
    }

    public String getSolatributos() {
        return solatributos;
    }

    public void setSolatributos(String solatributos) {
        this.solatributos = solatributos;
    }

    public String getSoleducper() {
        return soleducper;
    }

    public void setSoleducper(String soleducper) {
        this.soleducper = soleducper;
    }

    public String getSolrefs() {
        return solrefs;
    }

    public void setSolrefs(String solrefs) {
        this.solrefs = solrefs;
    }

    public String getSolsumevid() {
        return solsumevid;
    }

    public void setSolsumevid(String solsumevid) {
        this.solsumevid = solsumevid;
    }

    public String getSolestbusca() {
        return solestbusca;
    }

    public void setSolestbusca(String solestbusca) {
        this.solestbusca = solestbusca;
    }

    public Integer getSolmantenccod() {
        return solmantenccod;
    }

    public void setSolmantenccod(Integer solmantenccod) {
        this.solmantenccod = solmantenccod;
    }

    public String getSolmantenc() {
        return solmantenc;
    }

    public void setSolmantenc(String solmantenc) {
        this.solmantenc = solmantenc;
    }

    public Integer getSolsugenccod() {
        return solsugenccod;
    }

    public void setSolsugenccod(Integer solsugenccod) {
        this.solsugenccod = solsugenccod;
    }

    public String getSolsugenc() {
        return solsugenc;
    }

    public void setSolsugenc(String solsugenc) {
        this.solsugenc = solsugenc;
    }

    public Integer getSolcboprocod() {
        return solcboprocod;
    }

    public void setSolcboprocod(Integer solcboprocod) {
        this.solcboprocod = solcboprocod;
    }

    public String getSolcbopro() {
        return solcbopro;
    }

    public void setSolcbopro(String solcbopro) {
        this.solcbopro = solcbopro;
    }

    public Integer getSolcboespcod() {
        return solcboespcod;
    }

    public void setSolcboespcod(Integer solcboespcod) {
        this.solcboespcod = solcboespcod;
    }

    public String getSolcboesp() {
        return solcboesp;
    }

    public void setSolcboesp(String solcboesp) {
        this.solcboesp = solcboesp;
    }

    public String getSoldtnaspac() {
        return soldtnaspac;
    }

    public void setSoldtnaspac(String soldtnaspac) {
        this.soldtnaspac = soldtnaspac;
    }

    public String getSolmaenome() {
        return solmaenome;
    }

    public void setSolmaenome(String solmaenome) {
        this.solmaenome = solmaenome;
    }

    public int getSoljanecami() {
        return soljanecami;
    }

    public void setSoljanecami(int soljanecami) {
        this.soljanecami = soljanecami;
    }

    public Integer getSolcnspac() {
        return solcnspac;
    }

    public void setSolcnspac(Integer solcnspac) {
        this.solcnspac = solcnspac;
    }

    public String getSolpacnome() {
        return solpacnome;
    }

    public void setSolpacnome(String solpacnome) {
        this.solpacnome = solpacnome;
    }

    public String getSolpacsexo() {
        return solpacsexo;
    }

    public void setSolpacsexo(String solpacsexo) {
        this.solpacsexo = solpacsexo;
    }

    public Integer getSolpacsexocod() {
        return solpacsexocod;
    }

    public void setSolpacsexocod(Integer solpacsexocod) {
        this.solpacsexocod = solpacsexocod;
    }

    public String getSolpaccpf() {
        return solpaccpf;
    }

    public void setSolpaccpf(String solpaccpf) {
        this.solpaccpf = solpaccpf;
    }

    public Integer getSolsofcod() {
        return solsofcod;
    }

    public void setSolsofcod(Integer solsofcod) {
        this.solsofcod = solsofcod;
    }

    public String getSolsof() {
        return solsof;
    }

    public void setSolsof(String solsof) {
        this.solsof = solsof;
    }

    public String getSoldtenvresp() {
        return soldtenvresp;
    }

    public void setSoldtenvresp(String soldtenvresp) {
        this.soldtenvresp = soldtenvresp;
    }

    public String getSolresp() {
        return solresp;
    }

    public void setSolresp(String solresp) {
        this.solresp = solresp;
    }

    public String getSolhrenvresp() {
        return solhrenvresp;
    }

    public void setSolhrenvresp(String solhrenvresp) {
        this.solhrenvresp = solhrenvresp;
    }

    public String getSoljustdevresp() {
        return soljustdevresp;
    }

    public void setSoljustdevresp(String soljustdevresp) {
        this.soljustdevresp = soljustdevresp;
    }

    public int getSolrespaceita() {
        return solrespaceita;
    }

    public void setSolrespaceita(int solrespaceita) {
        this.solrespaceita = solrespaceita;
    }

    public String getSolavalativa() {
        return solavalativa;
    }

    public void setSolavalativa(String solavalativa) {
        this.solavalativa = solavalativa;
    }

    public String getSoldtavalin() {
        return soldtavalin;
    }

    public void setSoldtavalin(String soldtavalin) {
        this.soldtavalin = soldtavalin;
    }

    public String getSolhravalin() {
        return solhravalin;
    }

    public void setSolhravalin(String solhravalin) {
        this.solhravalin = solhravalin;
    }

    public Integer getSolsatisfcod() {
        return solsatisfcod;
    }

    public void setSolsatisfcod(Integer solsatisfcod) {
        this.solsatisfcod = solsatisfcod;
    }

    public String getSolsatisf() {
        return solsatisf;
    }

    public void setSolsatisf(String solsatisf) {
        this.solsatisf = solsatisf;
    }

    public Integer getSolclassifcod() {
        return solclassifcod;
    }

    public void setSolclassifcod(Integer solclassifcod) {
        this.solclassifcod = solclassifcod;
    }

    public String getSolclassif() {
        return solclassif;
    }

    public void setSolclassif(String solclassif) {
        this.solclassif = solclassif;
    }

    public Integer getSolevitrefcod() {
        return solevitrefcod;
    }

    public void setSolevitrefcod(Integer solevitrefcod) {
        this.solevitrefcod = solevitrefcod;
    }

    public String getSolevitref() {
        return solevitref;
    }

    public void setSolevitref(String solevitref) {
        this.solevitref = solevitref;
    }

    public Integer getSolindrefcod() {
        return solindrefcod;
    }

    public void setSolindrefcod(Integer solindrefcod) {
        this.solindrefcod = solindrefcod;
    }

    public String getSolindref() {
        return solindref;
    }

    public void setSolindref(String solindref) {
        this.solindref = solindref;
    }

    public String getSolcritsug() {
        return solcritsug;
    }

    public void setSolcritsug(String solcritsug) {
        this.solcritsug = solcritsug;
    }

    public String getRespativa() {
        return respativa;
    }

    public void setRespativa(String respativa) {
        this.respativa = respativa;
    }

    public int getSolatraso() {
        return solatraso;
    }

    public void setSolatraso(int solatraso) {
        this.solatraso = solatraso;
    }

    public int getSolatrasada() {
        return solatrasada;
    }

    public void setSolatrasada(int solatrasada) {
        this.solatrasada = solatrasada;
    }

    public int getSolsitcod() {
        return solsitcod;
    }

    public void setSolsitcod(int solsitcod) {
        this.solsitcod = solsitcod;
    }

    public String getSolsit() {
        return solsit;
    }

    public void setSolsit(String solsit) {
        this.solsit = solsit;
    }

    public String getSoldtsteleit() {
        return soldtsteleit;
    }

    public void setSoldtsteleit(String soldtsteleit) {
        this.soldtsteleit = soldtsteleit;
    }

    public String getSoldtavalfim() {
        return soldtavalfim;
    }

    public void setSoldtavalfim(String soldtavalfim) {
        this.soldtavalfim = soldtavalfim;
    }

    public String getSolavalfim() {
        return solavalfim;
    }

    public void setSolavalfim(String solavalfim) {
        this.solavalfim = solavalfim;
    }

    public String getSolhravalfim() {
        return solhravalfim;
    }

    public void setSolhravalfim(String solhravalfim) {
        this.solhravalfim = solhravalfim;
    }

    public int getSolprecomp() {
        return solprecomp;
    }

    public void setSolprecomp(int solprecomp) {
        this.solprecomp = solprecomp;
    }

    public Integer getSoltpo1() {
        return soltpo1;
    }

    public void setSoltpo1(Integer soltpo1) {
        this.soltpo1 = soltpo1;
    }

    public Integer getSoltpo2() {
        return soltpo2;
    }

    public void setSoltpo2(Integer soltpo2) {
        this.soltpo2 = soltpo2;
    }

    public Integer getSoltpo3() {
        return soltpo3;
    }

    public void setSoltpo3(Integer soltpo3) {
        this.soltpo3 = soltpo3;
    }

    public Integer getSoltpo4() {
        return soltpo4;
    }

    public void setSoltpo4(Integer soltpo4) {
        this.soltpo4 = soltpo4;
    }

    public Integer getSoltpo5() {
        return soltpo5;
    }

    public void setSoltpo5(Integer soltpo5) {
        this.soltpo5 = soltpo5;
    }

    public Integer getSoltpo6() {
        return soltpo6;
    }

    public void setSoltpo6(Integer soltpo6) {
        this.soltpo6 = soltpo6;
    }

    public Integer getSoltresp() {
        return soltresp;
    }

    public void setSoltresp(Integer soltresp) {
        this.soltresp = soltresp;
    }

    public Integer getSoltaval() {
        return soltaval;
    }

    public void setSoltaval(Integer soltaval) {
        this.soltaval = soltaval;
    }

    public Integer getSoltreg() {
        return soltreg;
    }

    public void setSoltreg(Integer soltreg) {
        this.soltreg = soltreg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solcod != null ? solcod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processos)) {
            return false;
        }
        Processos other = (Processos) object;
        if ((this.solcod == null && other.solcod != null) || (this.solcod != null && !this.solcod.equals(other.solcod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dashboard.entities.Processos[ solcod=" + solcod + " ]";
    }
    
}
