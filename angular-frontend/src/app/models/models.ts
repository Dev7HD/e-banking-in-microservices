enum accountType {
  SAVING_ACCOUNT,
  CURRENT_ACCOUNT,
}
interface customer {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}
interface account {
  balance: number;
  accountType: accountType;
  currency: string;
  customerInfo: customer;
}
