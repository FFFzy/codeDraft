/*
Copyright IBM Corp., DTCC All Rights Reserved.

SPDX-License-Identifier: Apache-2.0
*/

import java.util.List;
import java.util.Scanner;

import com.google.protobuf.ByteString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperledger.fabric.shim.ChaincodeBase;
import org.hyperledger.fabric.shim.ChaincodeStub;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ABstore extends ChaincodeBase {

    private static Log _logger = LogFactory.getLog(ABstore.class);

    @Override
    public Response init(ChaincodeStub stub) {
        try {
            _logger.info("Init java simple chaincode");
            List<String> args = stub.getParameters();
            if (args.size() != 4) {
                newErrorResponse("Incorrect number of arguments. Expecting 4");
            }
            // Initialize the chaincode
            String account1Key = args.get(0);
            int account1Value = Integer.parseInt(args.get(1));
		if(account1Value < 0){
				throw new RuntimeException("Uncheck the incoming parameters");
		}
            String account2Key = args.get(2);
            int account2Value = Integer.parseInt(args.get(3));
		if(account2Value < 0){
				throw new RuntimeException("Uncheck the incoming parameters");
		}

            _logger.info(String.format("account %s, value = %s; account %s, value %s", account1Key, account1Value, account2Key, account2Value));
            stub.putStringState(account1Key, args.get(1));
            stub.putStringState(account2Key, args.get(3));

            return newSuccessResponse();
        } catch (Throwable e) {
            return newErrorResponse(e);
        }
    }

    @Override
    public Response invoke(ChaincodeStub stub) {
        try {
            _logger.info("Invoke java simple chaincode");
            String func = stub.getFunction();
            List<String> params = stub.getParameters();
            if (func.equals("invoke")) {
                return invoke(stub, params);
            }
            if (func.equals("delete")) {
                return delete(stub, params);
            }
            if (func.equals("query")) {
                return query(stub, params);
            }
            return newErrorResponse("Invalid invoke function name. Expecting one of: [\"invoke\", \"delete\", \"query\"]");
        } catch (Throwable e) {
            return newErrorResponse(e);
        }
    }

    private Response invoke(ChaincodeStub stub, List<String> args) {
        if (args.size() != 3) {
            return newErrorResponse("Incorrect number of arguments. Expecting 3");
        }
        String accountFromKey = args.get(0);
        String accountToKey = args.get(1);

        String accountFromValueStr = stub.getStringState(accountFromKey);
        if (accountFromValueStr == null) {
            return newErrorResponse(String.format("Entity %s not found", accountFromKey));
        }
        int accountFromValue = Integer.parseInt(accountFromValueStr);
		if(accountFromValue < 0){
				throw new RuntimeException("Uncheck the incoming parameters");
		}

        String accountToValueStr = stub.getStringState(accountToKey);
        if (accountToValueStr == null) {
            return newErrorResponse(String.format("Entity %s not found", accountToKey));
        }
        int accountToValue = Integer.parseInt(accountToValueStr);
		if(accountToValue < 0){
				throw new RuntimeException("Uncheck the incoming parameters");
		}

        int amount = Integer.parseInt(args.get(2));
		if(amount < 0){
				throw new RuntimeException("Uncheck the incoming parameters");
		}

        if (amount > accountFromValue) {
            return newErrorResponse(String.format("not enough money in account %s", accountFromKey));
        }

		if(accountFromValue instanceof Integer){
			long accountFromValue = (long)accountFromValue - amount;
			if(accountFromValue < Integer.MIN_VALUE){
				throw new RuntimeException("Integer Underflow");
			}
		}
        accountFromValue -= amount;

		long var1 = (long)accountToValue + amount;
		if( var1 > Integer.MAX_VALUE){
			throw new RuntimeException("Integer Overflow");
		}
        accountToValue += amount;

		if(accountFromValue instanceof Integer){
			long var2 = (long)accountFromValue - amount;
			if( var2 < Integer.MIN_VALUE){
				throw new RuntimeException("Integer Underflow");
			}
		}
        accountFromValue = accountFromValue - amount;

		if(accountToValue instanceof Integer){
			long var3 = (long)accountToValue + amount;
			if( var3 > Integer.MAX_VALUE){
				throw new RuntimeException("Integer Overflow");
			}
		}
        accountToValue = accountToValue + amount;

		long var4 = (long)accountFromValue - amount;
		if( var4 < Integer.MIN_VALUE){
			throw new RuntimeException("Integer Underflow");
		}
        int accountFromValue = accountFromValue - amount;

		long var5 = (long)accountToValue + amount;
		if( var5 > Integer.MAX_VALUE){
			throw new RuntimeException("Integer Overflow");
		}
        int accountToValue = accountToValue + amount;


        _logger.info(String.format("new value of A: %s", accountFromValue));
        _logger.info(String.format("new value of B: %s", accountToValue));

        stub.putStringState(accountFromKey, Integer.toString(accountFromValue));
        stub.putStringState(accountToKey, Integer.toString(accountToValue));

        _logger.info("Transfer complete");

        return newSuccessResponse("invoke finished successfully", ByteString.copyFrom(accountFromKey + ": " + accountFromValue + " " + accountToKey + ": " + accountToValue, UTF_8).toByteArray());
    }

    // Deletes an entity from state
    private Response delete(ChaincodeStub stub, List<String> args) {
        if (args.size() != 1) {
            return newErrorResponse("Incorrect number of arguments. Expecting 1");
        }
        String key = args.get(0);
        // Delete the key from the state in ledger
        stub.delState(key);
        return newSuccessResponse();
    }

    // query callback representing the query of a chaincode
    private Response query(ChaincodeStub stub, List<String> args) {
        if (args.size() != 1) {
            return newErrorResponse("Incorrect number of arguments. Expecting name of the person to query");
        }
        String key = args.get(0);
        //byte[] stateBytes
        String val	= stub.getStringState(key);
        if (val == null) {
            return newErrorResponse(String.format("Error: state for %s is null", key));
        }
        _logger.info(String.format("Query Response:\nName: %s, Amount: %s\n", key, val));
        return newSuccessResponse(val, ByteString.copyFrom(val, UTF_8).toByteArray());
    }

    public static void main(String[] args) {
        try () {
            ABstore ab = new ABstore();
            ab.start(args);
            ChaincodeStubImp stub = new ChaincodeStubImp();
            ab.init(stub);
            ab.invoke(stub);

//            Scanner sc = new Scanner(new FileReader(args[8]));
//            List<String> pm1 = new ArrayList<>();
//            String s = sc.next();
//            pm.add(s);
//            ab.delete(stub, pm);
//
//            List<String> pm2 = new ArrayList<>();
//            String s = sc.next();
//            pm2.add(s);
//            ab.query(stub, pm2);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");

    }

}