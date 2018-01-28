package com.convertow.rest;

import com.convertow.interfaces.ConvertowFunctionsInterface;
import info.magnolia.rest.AbstractEndpoint;
import info.magnolia.rest.registry.ConfiguredEndpointDefinition;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.tools.PDFToImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

/**
 * Created by Miroslav on 16.1.2018.
 */
@Path("/pdftopng")
public class PdfToPngRestEndPoint<D extends ConfiguredEndpointDefinition> extends AbstractEndpoint<D> implements ConvertowFunctionsInterface{
    private static final Logger log = LoggerFactory.getLogger(PdfToPngRestEndPoint.class);

    @Inject
    public PdfToPngRestEndPoint(final D endpointDefinition) {
        super(endpointDefinition);
    }

    @GET
    @Path("/convert")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public Response getNewsByNodeName(@QueryParam("id") String id, @QueryParam("name") String name) throws RepositoryException{

        long startTime = System.currentTimeMillis();

        String nameWithoutExtension = name.substring(0, name.lastIndexOf('.'));
        /*String filePath = PATH + id + "\\" + name;*/
        String filePath = PATH + id + "\\MPLS_2015.pdf" ;
        File file = new File(filePath);

        PDDocument document = null;
        try {
            document = PDDocument.load(new File(file.getAbsolutePath()));
        }catch (Exception e){
            log.error("Can't make document from file -> " + name);
        }
        // render the pages
        String numPages = String.valueOf(document.getPages().getCount());
        String filePrefix = PATH + "\\" + id + "\\" + nameWithoutExtension;

        String [] args_2 =  new String[9];
        String pdfPath = file.getAbsolutePath();
        args_2[0] = "-startPage";
        args_2[1] = "1";
        args_2[2] = "-endPage";
        args_2[3] = numPages;
        args_2[4] = "-outputPrefix";
        args_2[5] = filePrefix;
        //args_2[6] = "-resolution";
        //args_2[7] = "1000";
        args_2[6] = pdfPath;
        args_2[7] = "-format";
        args_2[8] = "png";

        try {
            PDFToImage.main(args_2);
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }

        try {
            document.close();
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds for converting from PDF to PNG");
        return Response.ok().build();
    }

}
