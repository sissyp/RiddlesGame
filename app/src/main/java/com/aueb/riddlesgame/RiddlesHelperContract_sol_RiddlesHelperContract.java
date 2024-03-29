package com.aueb.riddlesgame;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class RiddlesHelperContract_sol_RiddlesHelperContract extends Contract {
    public static final String BINARY = "60806040526000805463ffffffff1916905566038d7ea4c6800060045534801561002857600080fd5b50600380546001600160a01b031916339081179091556040516000907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a3610cf0806100786000396000f3fe6080604052600436106100f35760003560e01c806386481d401161008a578063a4b8456911610059578063a4b84569146102ad578063b8b8d35a146102c0578063c8539c4b146102e0578063f2fde38b1461031657600080fd5b806386481d401461021b5780638bf948b71461023b5780638da5cb5b146102505780638f32d59b1461028257600080fd5b80636b8ff574116100c65780636b8ff57414610189578063715018a6146101b65780637b9b185c146101cb578063834d5fac146101eb57600080fd5b80632a08222b146100f8578063368929b41461011b5780635226dc931461013d5780635d9048f01461015d575b600080fd5b34801561010457600080fd5b506001546040519081526020015b60405180910390f35b34801561012757600080fd5b5061013b610136366004610a4d565b610336565b005b34801561014957600080fd5b5061013b610158366004610afe565b610342565b34801561016957600080fd5b5060005463ffffffff165b60405163ffffffff9091168152602001610112565b34801561019557600080fd5b506101a96101a4366004610b20565b610401565b6040516101129190610b86565b3480156101c257600080fd5b5061013b6104b7565b3480156101d757600080fd5b5061013b6101e6366004610b20565b610518565b3480156101f757600080fd5b5061020b610206366004610b20565b610534565b6040516101129493929190610b99565b34801561022757600080fd5b50610174610236366004610b20565b61060d565b34801561024757600080fd5b5061017461064a565b34801561025c57600080fd5b506003546001600160a01b03165b6040516001600160a01b039091168152602001610112565b34801561028e57600080fd5b506003546001600160a01b031633146040519015158152602001610112565b61013b6102bb366004610b20565b610682565b3480156102cc57600080fd5b5061013b6102db366004610b20565b6106ba565b3480156102ec57600080fd5b5061026a6102fb366004610b20565b6002602052600090815260409020546001600160a01b031681565b34801561032257600080fd5b5061013b610331366004610bd2565b61075c565b61033f8161077c565b50565b60008281526002602052604090205482906001600160a01b0316331461036757600080fd5b610372600383610bfb565b6103fc576103b9600180858154811061038d5761038d610c1d565b600091825260209091206001600290920201015463ffffffff6401000000009091048116919061089a16565b600184815481106103cc576103cc610c1d565b906000526020600020906002020160010160046101000a81548163ffffffff021916908363ffffffff1602179055505b505050565b60606001828154811061041657610416610c1d565b9060005260206000209060020201600001805461043290610c33565b80601f016020809104026020016040519081016040528092919081815260200182805461045e90610c33565b80156104ab5780601f10610480576101008083540402835291602001916104ab565b820191906000526020600020905b81548152906001019060200180831161048e57829003601f168201915b50505050509050919050565b6003546001600160a01b031633146104ce57600080fd5b6003546040516000916001600160a01b0316907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a3600380546001600160a01b0319169055565b6003546001600160a01b0316331461052f57600080fd5b600455565b6001818154811061054457600080fd5b906000526020600020906002020160009150905080600001805461056790610c33565b80601f016020809104026020016040519081016040528092919081815260200182805461059390610c33565b80156105e05780601f106105b5576101008083540402835291602001916105e0565b820191906000526020600020905b8154815290600101906020018083116105c357829003601f168201915b5050506001909301549192505063ffffffff808216916401000000008104821691600160401b9091041684565b60006001828154811061062257610622610c1d565b6000918252602090912060029091020160010154640100000000900463ffffffff1692915050565b600080546106649063ffffffff9081169060019061089a16565b6000805463ffffffff191663ffffffff929092169182179055919050565b600454341480156106a957506000818152600260205260409020546001600160a01b031633145b6106b257600080fd5b61033f6108cc565b60008181526002602052604090205481906001600160a01b031633146106df57600080fd5b6000600183815481106106f4576106f4610c1d565b906000526020600020906002020190508060010160089054906101000a900463ffffffff1660016107259190610c6e565b60018201805463ffffffff60401b1916600160401b63ffffffff938416810291909117918290556103fc9286929190910416610342565b6003546001600160a01b0316331461077357600080fd5b61033f8161092f565b604080516080810182528281526000805463ffffffff1660208084019190915292820181905260608201819052600180548082018255915281518051929360029092027fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf601926107ef928492019061099e565b50602082015160019091018054604084015160609094015163ffffffff908116600160401b0263ffffffff60401b199582166401000000000267ffffffffffffffff1990931691909416171792909216179055336002600061085660005463ffffffff1690565b63ffffffff16815260200190815260200160002060006101000a8154816001600160a01b0302191690836001600160a01b0316021790555061089661064a565b5050565b6000806108a78385610c6e565b90508363ffffffff168163ffffffff1610156108c5576108c5610ca4565b9392505050565b6003546001600160a01b031633146108e357600080fd5b60006108f76003546001600160a01b031690565b6040519091506001600160a01b038216904780156108fc02916000818181858888f19350505050158015610896573d6000803e3d6000fd5b6001600160a01b03811661094257600080fd5b6003546040516001600160a01b038084169216907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a3600380546001600160a01b0319166001600160a01b0392909216919091179055565b8280546109aa90610c33565b90600052602060002090601f0160209004810192826109cc5760008555610a12565b82601f106109e557805160ff1916838001178555610a12565b82800160010185558215610a12579182015b82811115610a125782518255916020019190600101906109f7565b50610a1e929150610a22565b5090565b5b80821115610a1e5760008155600101610a23565b634e487b7160e01b600052604160045260246000fd5b600060208284031215610a5f57600080fd5b813567ffffffffffffffff80821115610a7757600080fd5b818401915084601f830112610a8b57600080fd5b813581811115610a9d57610a9d610a37565b604051601f8201601f19908116603f01168101908382118183101715610ac557610ac5610a37565b81604052828152876020848701011115610ade57600080fd5b826020860160208301376000928101602001929092525095945050505050565b60008060408385031215610b1157600080fd5b50508035926020909101359150565b600060208284031215610b3257600080fd5b5035919050565b6000815180845260005b81811015610b5f57602081850181015186830182015201610b43565b81811115610b71576000602083870101525b50601f01601f19169290920160200192915050565b6020815260006108c56020830184610b39565b608081526000610bac6080830187610b39565b63ffffffff95861660208401529385166040830152509216606090920191909152919050565b600060208284031215610be457600080fd5b81356001600160a01b03811681146108c557600080fd5b600082610c1857634e487b7160e01b600052601260045260246000fd5b500690565b634e487b7160e01b600052603260045260246000fd5b600181811c90821680610c4757607f821691505b60208210811415610c6857634e487b7160e01b600052602260045260246000fd5b50919050565b600063ffffffff808316818516808303821115610c9b57634e487b7160e01b600052601160045260246000fd5b01949350505050565b634e487b7160e01b600052600160045260246000fdfea2646970667358221220e82d975047985d9cdeeb04982c3b4e114bc91823d562a8e973b323177253c19c64736f6c63430008090033";

    public static final String FUNC_AVATARTOOWNER = "avatarToOwner";

    public static final String FUNC_AVATARS = "avatars";

    public static final String FUNC_CREATENEWAVATAR = "createNewAvatar";

    public static final String FUNC_GETAVATARID = "getAvatarId";

    public static final String FUNC_GETAVATARSLENGTH = "getAvatarsLength";

    public static final String FUNC_GETLEVEL = "getLevel";

    public static final String FUNC_GETNAME = "getName";

    public static final String FUNC_GIVEHINT = "giveHint";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_LEVELUP = "levelUp";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETAVATARID = "setAvatarId";

    public static final String FUNC_SETHINTFEE = "setHintFee";

    public static final String FUNC_SOLVE = "solve";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected RiddlesHelperContract_sol_RiddlesHelperContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RiddlesHelperContract_sol_RiddlesHelperContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RiddlesHelperContract_sol_RiddlesHelperContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RiddlesHelperContract_sol_RiddlesHelperContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<String> avatarToOwner(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_AVATARTOOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> avatars(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_AVATARS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}));
        return new RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createNewAvatar(String _name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATENEWAVATAR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getAvatarId() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAVATARID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAvatarsLength() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAVATARSLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getLevel(BigInteger _avatarId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLEVEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getName(BigInteger _avatarId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> giveHint(BigInteger _avatarId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GIVEHINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> levelUp(BigInteger _avatarId, BigInteger _solved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LEVELUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId), 
                new org.web3j.abi.datatypes.generated.Uint256(_solved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAvatarId() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAVATARID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setHintFee(BigInteger _fee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETHINTFEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_fee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> solve(BigInteger _avatarId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SOLVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static RiddlesHelperContract_sol_RiddlesHelperContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RiddlesHelperContract_sol_RiddlesHelperContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RiddlesHelperContract_sol_RiddlesHelperContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RiddlesHelperContract_sol_RiddlesHelperContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RiddlesHelperContract_sol_RiddlesHelperContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RiddlesHelperContract_sol_RiddlesHelperContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RiddlesHelperContract_sol_RiddlesHelperContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RiddlesHelperContract_sol_RiddlesHelperContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RiddlesHelperContract_sol_RiddlesHelperContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RiddlesHelperContract_sol_RiddlesHelperContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RiddlesHelperContract_sol_RiddlesHelperContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RiddlesHelperContract_sol_RiddlesHelperContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<RiddlesHelperContract_sol_RiddlesHelperContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RiddlesHelperContract_sol_RiddlesHelperContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RiddlesHelperContract_sol_RiddlesHelperContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RiddlesHelperContract_sol_RiddlesHelperContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
