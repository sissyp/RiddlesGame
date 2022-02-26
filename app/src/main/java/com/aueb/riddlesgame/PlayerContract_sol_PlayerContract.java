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
public class PlayerContract_sol_PlayerContract extends Contract {
    public static final String BINARY = "60806040526000805463ffffffff60a01b1916905566038d7ea4c68000600181905560025534801561003057600080fd5b50600080546001600160a01b0319163390811782556040519091907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a3610e9e8061007f6000396000f3fe6080604052600436106101145760003560e01c8063834d5fac116100a0578063a4b8456911610064578063a4b8456914610300578063b8b8d35a14610313578063c8539c4b14610333578063e28e621e14610369578063f2fde38b1461038957600080fd5b8063834d5fac1461024957806386481d40146102795780638bf948b7146102995780638da5cb5b146102ae5780638f32d59b146102e057600080fd5b80636353586b116100e75780636353586b146101b15780636898f82b146101d45780636b8ff574146101e7578063715018a6146102145780637b9b185c1461022957600080fd5b80632a08222b14610119578063368929b41461013c5780635226dc931461015e5780635d9048f01461017e575b600080fd5b34801561012557600080fd5b506003546040519081526020015b60405180910390f35b34801561014857600080fd5b5061015c610157366004610bf2565b6103a9565b005b34801561016a57600080fd5b5061015c610179366004610ca3565b6103b5565b34801561018a57600080fd5b50600054600160a01b900463ffffffff165b60405163ffffffff9091168152602001610133565b6101c46101bf366004610cda565b610475565b6040519015158152602001610133565b61015c6101e2366004610cf7565b6104cb565b3480156101f357600080fd5b50610207610202366004610cf7565b61050a565b6040516101339190610d5d565b34801561022057600080fd5b5061015c6105c0565b34801561023557600080fd5b5061015c610244366004610cf7565b610621565b34801561025557600080fd5b50610269610264366004610cf7565b61063d565b6040516101339493929190610d70565b34801561028557600080fd5b5061019c610294366004610cf7565b610716565b3480156102a557600080fd5b5061019c610753565b3480156102ba57600080fd5b506000546001600160a01b03165b6040516001600160a01b039091168152602001610133565b3480156102ec57600080fd5b506000546001600160a01b031633146101c4565b61015c61030e366004610cf7565b6107a1565b34801561031f57600080fd5b5061015c61032e366004610cf7565b6107d4565b34801561033f57600080fd5b506102c861034e366004610cf7565b6004602052600090815260409020546001600160a01b031681565b34801561037557600080fd5b5061019c610384366004610cf7565b6108bb565b34801561039557600080fd5b5061015c6103a4366004610cda565b6108f7565b6103b281610917565b50565b60008281526004602052604090205482906001600160a01b031633146103da57600080fd5b6103e5600383610da9565b6104705761042d60016003858154811061040157610401610dcb565b600091825260209091206001600290920201015463ffffffff64010000000090910481169190610a4716565b6003848154811061044057610440610dcb565b906000526020600020906002020160010160046101000a81548163ffffffff021916908363ffffffff1602179055505b505050565b600080546001600160a01b0316331461048d57600080fd5b6040516001600160a01b038316903480156108fc02916000818181858888f193505050501580156104c2573d6000803e3d6000fd5b50600192915050565b60008181526004602052604090205481906001600160a01b031633146104f057600080fd5b60025434146104fe57600080fd5b610506610a79565b5050565b60606003828154811061051f5761051f610dcb565b9060005260206000209060020201600001805461053b90610de1565b80601f016020809104026020016040519081016040528092919081815260200182805461056790610de1565b80156105b45780601f10610589576101008083540402835291602001916105b4565b820191906000526020600020905b81548152906001019060200180831161059757829003601f168201915b50505050509050919050565b6000546001600160a01b031633146105d757600080fd5b600080546040516001600160a01b03909116907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a3600080546001600160a01b0319169055565b6000546001600160a01b0316331461063857600080fd5b600155565b6003818154811061064d57600080fd5b906000526020600020906002020160009150905080600001805461067090610de1565b80601f016020809104026020016040519081016040528092919081815260200182805461069c90610de1565b80156106e95780601f106106be576101008083540402835291602001916106e9565b820191906000526020600020905b8154815290600101906020018083116106cc57829003601f168201915b5050506001909301549192505063ffffffff808216916401000000008104821691600160401b9091041684565b60006003828154811061072b5761072b610dcb565b6000918252602090912060029091020160010154640100000000900463ffffffff1692915050565b600080546107749063ffffffff600160a01b909104811690600190610a4716565b6000805463ffffffff60a01b1916600160a01b63ffffffff93841681029190911791829055900416919050565b60008181526004602052604090205481906001600160a01b031633146107c657600080fd5b60015434146104fe57600080fd5b60008181526004602052604090205481906001600160a01b031633146107f957600080fd5b61083c60016003848154811061081157610811610dcb565b600091825260209091206001600290920201015463ffffffff600160401b90910481169190610a4716565b6003838154811061084f5761084f610dcb565b906000526020600020906002020160010160086101000a81548163ffffffff021916908363ffffffff160217905550610506826003848154811061089557610895610dcb565b6000918252602090912060029091020160010154600160401b900463ffffffff166103b5565b6000600382815481106108d0576108d0610dcb565b6000918252602090912060029091020160010154600160401b900463ffffffff1692915050565b6000546001600160a01b0316331461090e57600080fd5b6103b281610ad5565b604080516080810182528281526000805463ffffffff600160a01b909104166020808401919091529282018190526060820181905260038054600181018255915281518051929360029092027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b01926109939284920190610b43565b50602082015160019091018054604084015160609094015163ffffffff908116600160401b026bffffffff0000000000000000199582166401000000000267ffffffffffffffff19909316919094161717929092161790553360046000610a0760005463ffffffff600160a01b9091041690565b63ffffffff16815260200190815260200160002060006101000a8154816001600160a01b0302191690836001600160a01b03160217905550610506610753565b600080610a548385610e1c565b90508363ffffffff168163ffffffff161015610a7257610a72610e52565b9392505050565b6000546001600160a01b03163314610a9057600080fd5b600080546001600160a01b03166040519091506001600160a01b038216904780156108fc02916000818181858888f19350505050158015610506573d6000803e3d6000fd5b6001600160a01b038116610ae857600080fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b828054610b4f90610de1565b90600052602060002090601f016020900481019282610b715760008555610bb7565b82601f10610b8a57805160ff1916838001178555610bb7565b82800160010185558215610bb7579182015b82811115610bb7578251825591602001919060010190610b9c565b50610bc3929150610bc7565b5090565b5b80821115610bc35760008155600101610bc8565b634e487b7160e01b600052604160045260246000fd5b600060208284031215610c0457600080fd5b813567ffffffffffffffff80821115610c1c57600080fd5b818401915084601f830112610c3057600080fd5b813581811115610c4257610c42610bdc565b604051601f8201601f19908116603f01168101908382118183101715610c6a57610c6a610bdc565b81604052828152876020848701011115610c8357600080fd5b826020860160208301376000928101602001929092525095945050505050565b60008060408385031215610cb657600080fd5b50508035926020909101359150565b6001600160a01b03811681146103b257600080fd5b600060208284031215610cec57600080fd5b8135610a7281610cc5565b600060208284031215610d0957600080fd5b5035919050565b6000815180845260005b81811015610d3657602081850181015186830182015201610d1a565b81811115610d48576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610a726020830184610d10565b608081526000610d836080830187610d10565b63ffffffff95861660208401529385166040830152509216606090920191909152919050565b600082610dc657634e487b7160e01b600052601260045260246000fd5b500690565b634e487b7160e01b600052603260045260246000fd5b600181811c90821680610df557607f821691505b60208210811415610e1657634e487b7160e01b600052602260045260246000fd5b50919050565b600063ffffffff808316818516808303821115610e4957634e487b7160e01b600052601160045260246000fd5b01949350505050565b634e487b7160e01b600052600160045260246000fdfea264697066735822122093f012560058ad20eb87a64f7345dd62b28f19d2668783d7eb0ee8a8b607e1e364736f6c63430008090033";

    public static final String FUNC_AVATARTOOWNER = "avatarToOwner";

    public static final String FUNC_AVATARS = "avatars";

    public static final String FUNC_CREATENEWAVATAR = "createNewAvatar";

    public static final String FUNC_GETAVATARID = "getAvatarId";

    public static final String FUNC_GETAVATARSLENGTH = "getAvatarsLength";

    public static final String FUNC_GETLEVEL = "getLevel";

    public static final String FUNC_GETNAME = "getName";

    public static final String FUNC_GETSOLVED = "getSolved";

    public static final String FUNC_GIVEHINT = "giveHint";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_LEVELUP = "levelUp";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PLAY = "play";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_REWARD = "reward";

    public static final String FUNC_SETAVATARID = "setAvatarId";

    public static final String FUNC_SETHINTFEE = "setHintFee";

    public static final String FUNC_SOLVE = "solve";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<BigInteger> getSolved(BigInteger _avatarId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSOLVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<TransactionReceipt> play(BigInteger _avatarId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PLAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reward(String _To) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REWARD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _To)), 
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
    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
