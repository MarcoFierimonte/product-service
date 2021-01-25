package com.lastminute.store.product.boot;

import com.lastminute.store.product.exception.MalformedInputExceptionException;
import com.lastminute.store.product.inout.ExtractProduct;
import com.lastminute.store.product.inout.ViewOrder;
import com.lastminute.store.product.inout.api.DataReader;
import com.lastminute.store.product.inout.api.DataViewer;
import com.lastminute.store.product.model.Order;
import com.lastminute.store.product.model.Product;
import com.lastminute.store.product.service.OrderService;
import com.lastminute.store.product.service.api.BuildOrder;
import com.lastminute.store.product.util.Utility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static DataReader<Product> extractProduct = new ExtractProduct();
    private static DataViewer<Order> dataViewer = new ViewOrder();

    /**
     * Mandatory arguments: <br>
     * - dataFolder: the folder path containing the cvs files with data <br>
     * - files: comma separated names for the file to use. Example files=Input1.csv,Input2.csv
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        String[] params = argumentsExtractor(args);
        String dataFolder = params[0];
        String files = params[1];
        String[] allFiles = files.split(",");
        // process each input file
        for (String currentFile : allFiles) {
            Path path = Paths.get(dataFolder + File.separator + currentFile);
            List<String> data = Utility.readFile(path.toFile());
            List<Product> products = new ArrayList<>();
            for (int n = 0; n < data.size(); n++) {
                if (n == 0) {
                    // jump the first line: expected a comment line
                    continue;
                }
                String line = data.get(n);
                Product product = extractProduct.extract(line);
                products.add(product);
            }
            // fullfit the order
            BuildOrder orderService = new OrderService();
            Order order = orderService.build(products);
            // display output
            dataViewer.view(order);
        }
    }

    private static String[] argumentsExtractor(String[] args) {
        if (args.length == 0) {
            throw new MalformedInputExceptionException("Needed at least one argument: [files, dataFolder]");
        }
        String dataFolder = null;
        String files = null;
        for (String curr : args) {
            String key = curr.split("=")[0];
            switch (key) {
                case ("dataFolder"):
                    dataFolder = curr.split("=")[1]; // get the value
                    break;
                case ("files"):
                    files = curr.split("=")[1]; // get the value
                    break;
                default:
                    throw new MalformedInputExceptionException("Not recognized argument, accepted only: [files, dataFolder]. Current arguments=[" + String.join(" ", args) + "]");
            }
        }
        if(dataFolder == null || files == null) {
            throw new MalformedInputExceptionException("Missing some argument: [files, dataFolder]. Current arguments=[" + String.join(" ", args) + "]");
        }
        return new String[]{dataFolder, files};
    }

}
