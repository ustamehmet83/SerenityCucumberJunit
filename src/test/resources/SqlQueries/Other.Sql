select s.code,p.Code,s.ProductSubType,s.ClassCode from NetTrexFI_Dev.dbo.secSecurity s
                                                           INNER JOIN NetTrexFI_Dev.dbo.prdInstrument p on s.InstrumentId=p.Id;

NetTrexFI_Dev.dbo.[sp_FIOutstandings]'20240522';

select * from NetTrexMM_Dev.dbo.prdProductDefinition;

select Name,AccountingMapCode from NetTrexMM_Dev.dbo.prdProductDefinition;


select  TxnTypeCode,Name from NetTrexMM_Dev.dbo.defTxnType;

select * from NetTrexFI_Dev.dbo.[fn_DepoBalance](null,null,null,null,'20240522',null)

select Name,AccountingMapCode from NetTrexMM_Dev.dbo.prdProductDefinition;

NetTrexFI_Dev.dbo.[sp_FIOutstandingsWithCalcs] '20240523';

select PortfolioId from NetTrexFI_Dev.dbo.txnFI where LogicalReference='TRS-FI-240514-0016'


select Path from NetTrexFI_Dev.dbo.prfPortfolio where Id= (select PortfolioId from NetTrexFI_Dev.dbo.txnFI where LogicalReference='TRS-FI-240514-0016')

select s.code,p.Code,s.ProductSubType,s.ClassCode,s.FaceCcy from NetTrexFI.dbo.secSecurity s INNER JOIN NetTrexFI.dbo.prdInstrument p on s.InstrumentId=p.Id