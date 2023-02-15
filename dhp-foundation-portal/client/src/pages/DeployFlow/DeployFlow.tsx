import React, { useEffect, useState } from "react";
import { useDispatch } from 'react-redux';
import { changeTitle } from "../../store/title/titleSlice";
import MultiStepDeployFlow from "components/MultiStepDeployFlow/MultiStepDeployFlow";

const DeployFlow = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(changeTitle("DeployFlow"));
  });

  return <>
    <MultiStepDeployFlow />
  </>
}

export default DeployFlow;